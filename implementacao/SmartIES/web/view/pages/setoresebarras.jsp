<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Setor e barras</title>

        <script src="view/resources/javascript/queue.v1.min.js"></script> <!-- Biblioteca para agilizar o carregamento do mapa -->
        <script src="view/resources/javascript/d3.v3.min.js"></script> <!-- Biblioteca D3.js -->
        <script src="view/resources/javascript/topojson.v1.min.js"></script> <!-- Biblioteca para utilizar mapas com D3.js -->
        <script src="view/resources/javascript/dashboards.js"></script>
        <link rel="stylesheet" href="view/resources/css/setoresebarras.css" type="text/css"/>
        <link rel="stylesheet" href="view/resources/css/estilo.css" type="text/css"/>
        <!--<link rel="stylesheet" href="${facesContext.externalContext.requestContextPath}/view/resources/css/estilo.css" type="text/css"/>-->
    </head>
    <body onLoad="plotaGraficos();">
        <c:import url="/view/includes/menu.jsp"></c:import>
        <div class="container">      	
            <h2 class="page-header"><span class="glyphicon glyphicon-object-align-bottom"></span> Setor e barras (quantidade de aulas)</h2>
            <button onClick="plotaGraficos();" class="botao">Desenhar +1</button>
            <div id="dashboard" class="caixa"></div>
        </div>
        <script>
            function plotaGraficos() {
                var freqData = [
                    {State: 'JAN', freq: {Alex: 786, Thesko: 319, Rosangela: 249, Professor4: 786}}
                    , {State: 'FEV', freq: {Alex: 101, Thesko: 412, Rosangela: 674, Professor4: 101}}
                    , {State: 'MAR', freq: {Alex: 932, Thesko: 149, Rosangela: 418, Professor4: 032}}
                    , {State: 'ABR', freq: {Alex: 832, Thesko: 152, Rosangela: 862, Professor4: 932}}
                    , {State: 'MAI', freq: {Alex: 481, Thesko: 304, Rosangela: 948, Professor4: 481}}
                    , {State: 'JUN', freq: {Alex: 619, Thesko: 167, Rosangela: 063, Professor4: 619}}
                    , {State: 'JUL', freq: {Alex: 819, Thesko: 247, Rosangela: 203, Professor4: 819}}
                    , {State: 'AGO', freq: {Alex: 498, Thesko: 852, Rosangela: 942, Professor4: 498}}
                    , {State: 'SET', freq: {Alex: 797, Thesko: 849, Rosangela: 534, Professor4: 897}}
                    , {State: 'OUT', freq: {Alex: 162, Thesko: 379, Rosangela: 471, Professor4: 262}}
                ];
                dashboard('#dashboard', freqData);
            }
        </script>
    </body>
</html>