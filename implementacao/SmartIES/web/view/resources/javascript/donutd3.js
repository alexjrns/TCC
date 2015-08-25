function plotaGraficos(dataFile){
    var width = 960,
            height = 500,
            radius = Math.min(width, height) / 2;

    var color = d3.scale.ordinal()
            .range(["steelblue", "#807dba", "#e08214", "#41ab5d", "#8ea253", "#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);
            //.range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);
            //.range(["#3399FF", "#5DAEF8", "#86C3FA", "#ADD6FB", "#D6EBFD"]);

    var arc = d3.svg.arc()
            .outerRadius(radius - 10)
            .innerRadius(radius - 70);

    var pie = d3.layout.pie()
            .sort(null)
            .value(function(d) { return d.quantity; });

    var tip = d3.tip()
            .attr('class', 'd3-tip')
            .offset([-10, 0])
            .html(function(d) {
                    return "<span style='color:white'>" + d.data.quantity + ((d.data.quantity > 1)? " publica&ccedil;&otilde;es": " publica&ccedil;&atilde;o") + "</span>" ;
            })

    var svg = d3.select("#donutchart").append("svg")
            .attr("width", width)
            .attr("height", height)
      .append("g")
            .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");
    
    svg.append("text")
       .attr("text-anchor", "middle")
       .attr("dy", ".35em")
       .attr("class", "chartLabel")
       .text("Qualis");

    svg.append("g")
            .attr("class", "labels");
    svg.append("g")
            .attr("class", "lines");

    svg.call(tip);

    d3.csv(dataFile, function(error, data) {

            data.forEach(function(d) {
                    d.quantity = +d.quantity;
            });

            var arcOver = d3.svg.arc()
               .innerRadius(radius - 70)
               .outerRadius(radius - 2);

            var g = svg.selectAll(".arc")
                    .data(pie(data))
            .enter().append("g")
                    .attr("class", "arc")
                    .on('mouseover', tip.show)
                    .on('mouseout', tip.hide)
                    .on("click", function(d) { filtraClique(d); return 0;});

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
                    .style("fill", function(d) { return color(d.data.classification); })

                    .transition()
                            .ease("exp")
                            .duration(1000)
                            .attrTween("d", tweenPie);

                    function tweenPie(b) {
                      var i = d3.interpolate({startAngle: 2.1*Math.PI, endAngle: 2.1*Math.PI}, b);
                      return function(t) { return arc(i(t)); };
                    }

            g.append("text")
                    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
                    .attr("dy", ".35em")
                    .style("text-anchor", "middle")
                    .text(function(d) { return d.data.classification; });

            function filtraClique(d){
                    console.log(d.data.quantity);
                    $('#myModalLabel').html("<span><b>Qualis: </b>" + d.data.classification + "\t | <b>Quantidade: </b>" + d.data.quantity + "</span>");
                    $('#modal-body').html("<strong>Peri&oacute;dicos / Confer&ecirc;ncias</strong><br>");
                    $('#modal-body').append("<span>- " + d.data.periodicos.replace(/\*/g, "<br>- ") + "</span>");
                    $('#myModal').modal('show');
            }
    });
}