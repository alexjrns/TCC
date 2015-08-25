<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Mapa do Brasil</title>

        <script src="view/resources/javascript/queue.v1.min.js"></script> <!-- Biblioteca para agilizar o carregamento do mapa -->
        <script src="view/resources/javascript/d3.v3.min.js"></script> <!-- Biblioteca D3.js -->
        <script src="view/resources/javascript/topojson.v1.min.js"></script> <!-- Biblioteca para utilizar mapas com D3.js -->
        <script src="view/resources/javascript/mapa.js"></script>
        <link rel="stylesheet" href="view/resources/css/mapa.css" type="text/css"/>
        <link rel="stylesheet" href="view/resources/css/estilo.css" type="text/css"/>

    </head>
    <body>

        <c:import url="/view/includes/menu.jsp"></c:import>
        <div class="container">
<h2 class="page-header"><span class="glyphicon glyphicon-globe"></span> Mapa do brasil</h2>            
            <button onClick="plotarMapa('view/resources/javascript/br-states_min.json');" class="botao">Desenhar</button>
            <div id="teste">
            </div>
        </div>
    </body>
</html>