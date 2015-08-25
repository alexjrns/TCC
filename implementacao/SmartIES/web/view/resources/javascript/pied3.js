function plotaGraficos(dataFile){
	var formatPercent = d3.format("%");

	var width = 960,
		height = 500,
		radius = Math.min(width, height) / 2;

	var color = d3.scale.ordinal()
		//.range(["steelblue", "#807dba", "#e08214", "#41ab5d", "#8ea253", "#98abc5", "#8a89a6"]);
		.range(["#807dba", "#e08214", "#41ab5d", "#8ea253", "#98abc5", "#8a89a6"]);

	var arc = d3.svg.arc()
		.outerRadius(radius - 10)
		.innerRadius(0);
		
	var arcOver = d3.svg.arc()
	   .outerRadius(radius - 2);

	var pie = d3.layout.pie()
		.sort(null)
		.value(function(d) { return d.quantity; });
		
	var tip = d3.tip()
			.attr('class', 'd3-tip')
			.offset([10, 0])
			.html(function(d) {
				return "<span style='color:white'>" + formatPercent(d.data.quantity) + " de " + d.data.type + "</span>" ;
			});

	var svg = d3.select("#piechart").append("svg")
			.attr("width", width)
			.attr("height", height)
		.append("g")
			.attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

	svg.call(tip);
		
	d3.csv(dataFile, function(error, data) {

		data.forEach(function(d) {
			d.quantity = +d.quantity;
		});

	  var g = svg.selectAll(".arc")
		  .data(pie(data))
		.enter().append("g")
			.attr("class", "arc")
			.on('mouseover', tip.show)
			.on('mouseout', tip.hide);

		g.append("path")
			.attr("d", arc)
			.on('mouseover', function(d) {
				d3.select(this)
				   .transition()
				   .duration(100)
				   .attr("d", arcOver);
			})
			.on("mouseleave", function(d) {
				d3.select(this).transition()            
				   .attr("d", arc)
				   .attr("stroke","none");
			})
			.style("fill", function(d) { return color(d.data.type); })
			.transition()
			.ease("back") //back, bounce, elastic, exp, circle
			.duration(900)
			.attrTween("d", tweenPie);

			function tweenPie(b) {
			  var i = d3.interpolate({startAngle: 2.1*Math.PI, endAngle: 2.1*Math.PI}, b);
			  return function(t) { return arc(i(t)); };
			}

	  g.append("text")
		  .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
		  .attr("dy", ".35em")
		  .style("text-anchor", "middle")
		  .style("font-size", "12px")
		  .text(function(d) { return d.data.type; });

	});
}