function plotaGraficos(dataFile) {
    console.log(dataFile);
    var margin = {top: 20, right: 20, bottom: 30, left: 40},
    width = 1130 - margin.left - margin.right,
            height = 500 - margin.top - margin.bottom;

    var x = d3.scale.ordinal()
            .rangeRoundBands([0, width], .1);

    var y = d3.scale.linear()
            .domain([0, 100])
            //.range([height, 0]);
            .rangeRound([0, height]);

    var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom");

    var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left")
            .ticks(10, "d");

    var tip = d3.tip()
            .attr('class', 'd3-tip')
            .offset([-10, 0])
            .html(function (d) {
                return "<span style='color:#333'>" + d.quantity + ((d.quantity > 1) ? " publica&ccedil;&otilde;es": " publica&ccedil;&atilde;o") + "</span>";
            })

    var svg = d3.select("#barchart").append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    svg.call(tip);

    d3.tsv(dataFile, type, function (error, data) {
        if (error)
            throw error;

        x.domain(data.map(function (d) {
            return d.type;
        }));
        y.domain([0, d3.max(data, function (d) {
                return d.quantity;
            })]);

        svg.append("g")
                .attr("class", "x axis")
                .attr("transform", "translate(0," + height + ")")
                .call(xAxis);

        svg.append("g")
                .attr("class", "y axis")
                .call(yAxis)
                .append("text")
                .attr("transform", "rotate(-90)")
                .attr("y", 6)
                .attr("dy", ".71em")
                .style("text-anchor", "end")
                .text("Quantidade de publicacoes");

        var bar = svg.selectAll(".bar")
                .data(data)
                .enter();

        bar.append("rect")
                .attr("class", "bar")
                /*.attr("x", function(d) {
                 console.log(formatText(d.type));
                 return formatText(d.type);
                 //return x(d.type);
                 }
                 )*/
                .attr("x", function (d) {
                    return x(d.type);
                })
                //.attr("x", function(d) { return x(d.type) - .5; })
                .attr("y", function (d) {
                    return height - 0.5;
                })
                .attr("width", 100)
                .attr("height", function (d) {
                    return 0;
                })
                .on('mouseover', tip.show)
                .on('mouseout', tip.hide)
                .on("click", function (d) {
                    filtraClique(d);
                    return 0;
                })
                .transition().delay(function (d, i) {
            return i * 300;
        })
                .duration(1000)
                .attr("height", function (d) {
                    return y(d.quantity);
                })
                .attr("y", function (d) {
                    return height - y(d.quantity) - .5;
                });
    });

    function type(d) {
        d.quantity = +d.quantity;
        return d;
    }

    function filtraClique(d) {
        console.log(d.type);
        $('#myModalLabel').html("<span><b>Tipo de publica&ccedil;&atilde;o: </b>" + d.type + "\t | <b>Quantidade: </b>" + d.quantity + "</span>");
        //$('#modal-body').append("<span>Quantidade: " + d.quantity + "</span>");
        $('#myModal').modal('show');
    }

    function formatText(text) {
        text = text.trim();
        letra = text.charAt(0);
        letra = letra.toUpperCase();
        text = letra + text.substring(1, text.length);
        texto = "";
        i = 0;
        while (i < text.length) {
            if ((i > 0) && (text[i] == text[i].toUpperCase())) {
                aux = text.substring(0, i);
                texto = aux + " " + text.substring(i, text.length);
            }
            i++;
        }
        console.log(texto);
        return text;
    }
}

