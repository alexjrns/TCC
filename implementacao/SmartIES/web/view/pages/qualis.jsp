<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Qualis</title>

        <script src="view/resources/javascript/queue.v1.min.js"></script> <!-- Biblioteca para agilizar o carregamento do mapa -->
        <script src="view/resources/javascript/d3.v3.min.js"></script> <!-- Biblioteca D3.js -->
        <script src="view/resources/javascript/donutd3.js"></script>
        <script src="view/resources/javascript/d3.tip.v0.6.3.js"></script>
        <link rel="stylesheet" href="view/resources/css/qualis.css" type="text/css"/>
        <link rel="stylesheet" href="view/resources/css/estilo.css" type="text/css"/>
        <!--<link rel="stylesheet" href="${facesContext.externalContext.requestContextPath}/view/resources/css/estilo.css" type="text/css"/>-->
    </head>
    <body onLoad="plotaGraficos('view/resources/jsons/data.csv');">
        <c:import url="/view/includes/menu.jsp"></c:import>
        <div class="container">      	
            <h2 class="page-header"><i class="fa fa-pie-chart"></i> Qualis</h2>
            <button onClick="plotaGraficos('view/resources/jsons/data.csv');" class="botao">Desenhar +1</button>
            <div id="donutchart" class="caixa"></div>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
              <div class="modal-dialog" role="document">
                    <div class="modal-content bordaReta">
                      <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h5 class="modal-title" id="myModalLabel">Valor selecionado</h5>
                      </div>
                      <div class="modal-body" id="modal-body">
                            ...
                      </div>
                    </div>
              </div>
            </div>
        </div>
    </body>
</html>