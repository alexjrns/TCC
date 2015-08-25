function plotaGraficos(data_file){
var margin = {top: 20, right: 25, bottom: 30, left: 60},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

var x = d3.scale.ordinal()
	.rangePoints([0, width]);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left");

var line = d3.svg.line()
	//.interpolate("bundle")
    //.tension(0.8)
    .x(function(d) { return x(d.name); })
    .y(function(d) { return y(d.close); });

var tip = d3.tip()
    .attr('class', 'd3-tip')
    .offset([-10, 0])
    .html(function (d) {
        //return "<span style='color:#333'>" + d.close + ((d.close > 1) ? " aulas": " aula") + "</span>";
        return "<span style='color:#333; font-weight: bold; font-size: 20px;'>" + d.name + "</span><br>"
                + "<span style='color:#333; font-weight: normal;'>" + d.close + ((d.close > 1) ? " aulas": " aula") + "</span>";
    });

var svg = d3.select("#linechart").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
svg.call(tip);
        
var circleContainer,
	detailWidth  = 98,
	detailHeight = 55,
	detailMargin = 10;

d3.tsv(data_file, function(error, data) {
  if (error) throw error;

  data.forEach(function(d) {
    d.close = +d.close;
  });

	x.domain(data.map(function(d) { return d.name; }));
	y.domain(d3.extent(data, function(d) { return d.close; }));

  svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis);
  svg.append("g")
      .attr("class", "y axis")
	  .attr("transform", "translate(-25,0)")
      .call(yAxis)
    .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", ".71em")
      .style("text-anchor", "end")
      .text("Aulas");

  svg.append("path")
      .datum(data)
	  .attr("class", "line")
          .attr("stroke-width", "0.01px")
	  .attr("d", line)
      .transition()
        .duration(500)
		//.delay( 500 / 2 )
            .attr("stroke-width", "4.5px")
	  .attrTween( 'd', tween( data, line ) )
		.each( 'end', function() {
          drawCircles( data );
        } );
		
	
	function drawCircle( datum, index ) {
		circleContainer.datum( datum )
			.append('circle')
			//.attr('class', 'dot')
                        .attr('class', classe)
			.attr('r', 0)
			.attr('cx', function( d ) {return x( d.name );})
			.attr('cy',function( d ) {return y( d.close );})
			.on('mouseenter', function( d ) {
			  d3.select( this )
				.attr('class','dotHover')
				//.attr( 'r', 9 );
                        	.attr( 'r', 9 ),
                                tip.show(d);
			})
                        //.on('mouseover', tip.show)
			.on('mouseout', function( d ) {
			  d3.select( this )
				.attr('class','dot')
				.attr( 'r', 5 ),
                                tip.hide(d);
			})
			//.on('click touch', function( d ) {
			.on('click', function( d ) {
			  console.log(d.close);
				
			})
			.transition()
			// .delay( 1500 / 10 * index )
			.attr( 'r', 5);
                function classe(d){
                   return ( d > 0 ? 'dot' : 'dot2');
                }
    }
    
    function drawCircles( data ) {
      circleContainer = svg.append( 'g' );

      data.forEach( function( datum, index ) {
        drawCircle( datum, index );
      } );
    }

    function tween( b, callback ) {
      return function( a ) {
        var i = d3.interpolateArray( a, b );

        return function( t ) {
          return callback( i ( t ) );
        };
      };
    }
});
}