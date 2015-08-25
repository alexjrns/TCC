function plotarMapa(arquivoJSON){
	var width = 1024,
		height = 750,
		active = d3.select(null);

	var projection = d3.geo.albers()
		.center([-50, -14])
		.rotate([4.4, 0])
		.parallels([-30, 15])
		.scale(1000)
		.translate([width / 2, height / 2]);

	var path = d3.geo.path()
		.projection(projection);

	var svg = d3.select("body").append("svg")
		.attr("width", width)
		.attr("height", height);			

	svg.append("rect")
		.attr("class", "background")
		.attr("width", width)
		.attr("height", height)
		.on("click", reset);

	var g = svg.append("g")
		.style("stroke-width", "1.5px");

	var div = d3.select("body").append("div")   
		.attr("class", "tooltip")               
		.style("opacity", 0);
	console.log(arquivoJSON);	
	queue()
		.defer(d3.json, arquivoJSON)
                //.defer(d3.json, "br-states_min.json")
        
		
		.await(ready);
		
	function ready(error, br_states){
		if(error) return console.error(error);

		g.selectAll("path")
			.data(topojson.feature(br_states, br_states.objects.states).features)
			.enter().append("path")
			.attr("d", path)
			.attr("class", "feature")
			.on("mouseover", function(d) {      
                                //d3.select( this )
                                    //.attr('class', 'naue');
                                    div.transition()        
					.duration(100)      
					.style("opacity", .9);      
				div.html(d.properties.name)
					.style("left", (d3.event.pageX) + "px")     
					.style("top", (d3.event.pageY - 28) + "px");
				})
			.on("mouseout", function() {
                            d3.select( this )
                                .attr("class", "feature");
                            limpaTooltip();
			})
			.on("click", clicked);
	
		g.append("path")
			.datum(topojson.mesh(br_states, br_states.objects.states, function(a, b) { return a !== b; }))
			.attr("class", "mesh")
			.attr("d", path);			
	}

	function clicked(d) {
	  if (active.node() === this) return reset();
	  active.classed("active", false);
	  active = d3.select(this).classed("active", true);
	  
	  var bounds = path.bounds(d),
		  dx = bounds[1][0] - bounds[0][0],
		  dy = bounds[1][1] - bounds[0][1],
		  x = (bounds[0][0] + bounds[1][0]) / 2,
		  y = (bounds[0][1] + bounds[1][1]) / 2,
		  scale = .9 / Math.max(dx / width, dy / height),
		  translate = [width / 2 - scale * x, height / 2 - scale * y];

	  g.transition()
		  .duration(750)
		  .style("stroke-width", 1.5 / scale + "px")
		  .attr("transform", "translate(" + translate + ")scale(" + scale + ")");
	  passaValor(d);
	  limpaTooltip();
	}

	function limpaTooltip(){
		div.transition().duration(100)
		  .style("opacity", "0");
		d3.select(this)
		  .attr("fill", "transparent");		
	}

	function reset() {
	  active.classed("active", false);
	  active = d3.select(null);

	  g.transition()
		  .duration(750)
		  .style("stroke-width", "1.5px")
		  .attr("transform", "");
		passaValor(null);
		limpaTooltip();
	}

	function passaValor(d){
		var dive = document.querySelector("#teste");
		if(d!=null){
			dive.innerHTML = "Estado selecionado: " + d.properties.name + " | Regi&atilde;o: " + d.properties.region;
		} else{
			dive.innerHTML = "Nenhum estado selecionado!";
		}
	}
}