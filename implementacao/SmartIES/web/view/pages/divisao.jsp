<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Divisão</title>

        <script src="view/resources/javascript/queue.v1.min.js"></script> <!-- Biblioteca para agilizar o carregamento do mapa -->
        <script src="view/resources/javascript/d3.v3.min.js"></script> <!-- Biblioteca D3.js -->
        <script src="view/resources/javascript/pied3.js"></script>
        <script src="view/resources/javascript/d3.tip.v0.6.3.js"></script>
        <link rel="stylesheet" href="view/resources/css/qualis.css" type="text/css"/>
        <link rel="stylesheet" href="view/resources/css/estilo.css" type="text/css"/>
        <!--<link rel="stylesheet" href="${facesContext.externalContext.requestContextPath}/view/resources/css/estilo.css" type="text/css"/>-->
    </head>
    <body onLoad="plotaGraficos('view/resources/jsons/data-pie2.csv');">
        <c:import url="/view/includes/menu.jsp"></c:import>
        <div class="container">      	
            <h2 class="page-header"><i class="fa fa-pie-chart"></i> Divisão do tempo em aulas práticas e teóricas</h2>
            <button onClick="plotaGraficos('view/resources/jsons/data-pie.csv');" class="botao">Desenhar +1</button>
            <div id="piechart" class="caixa"></div>

        </div>
    </body>
</html>