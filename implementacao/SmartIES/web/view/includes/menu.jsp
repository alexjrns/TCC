<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="view/resources/images/favicon/fav_16.png" sizes="16x16">
<link rel="icon" href="view/resources/images/favicon/fav_32.png" sizes="32x32">
<link rel="icon" href="view/resources/images/favicon/fav_48.png" sizes="48x48">

<link rel="stylesheet" href="view/resources/css/bootstrap.css" type="text/css"/>
<link rel="stylesheet" href="view/resources/css/menu.css" type="text/css"/>
<link rel="stylesheet" href="view/resources/css/font-awesome.min.css" type="text/css"/>
<link rel="stylesheet" href="view/resources/css/estilo.css" type="text/css"/>
<link rel="stylesheet" href="view/resources/css/elegant_icon.css" type="text/css"/>

<script type="text/javascript" src="view/resources/javascript/jquery.min.js"></script>
<script type="text/javascript" src="view/resources/javascript/bootstrap.js"></script>
<script type="text/javascript" src="view/resources/javascript/jquery-ui.min.js"></script>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="img-menu">
                <a href="home"><div class="img-int"></div></a>
            </div>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon_easel"></span> Dashboards <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="dropdown-header">Sistema Acadêmico</li>
                        <li><a href="dashboards?ds=divisao">Divisão do tempo</a></li>
                        <li><a href="dashboards?ds=setoresebarras">Quantidade de aulas</a></li>
                        <li><a href="dashboards?ds=quantidadeaulas">Tendência (Quantidade de aulas)</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Lattes</li>
                        <li><a href="dashboards?ds=mapabrasil">Mapa do Brasil</a></li>
                        <li><a href="dashboards?ds=qualis">Qualis</a></li>
                        <li><a href="dashboards?ds=quantidade">Quantidade</a></li>
                        <!--<li class="divider"></li>
                        <li class="dropdown-header">Discentes</li>
                        <li><a href="representante?acao=cad">Cadastrar</a></li>
                        <li><a href="representante?acao=lst">Listar</a></li>-->
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon_table"></span> Tabelas <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon_cogs"></span> Sistema <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="dropdown-header">Notificações</li>
                        <li><a href="notificacoes?act=cad">Cadastrar</a></li>
                        <li><a href="notificacoes?act=lst">Visualizar</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon_profile"></span> ${sessionScope.usuarioLogado.nome} <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <h3 class="menu_usuario"><span class="glyphicon glyphicon-user"></span> ${sessionScope.usuarioLogado.nome}</h3>
                            <p class = "mini_span">
                                <span>último login: ${sessionScope.usuarioLogado.getUltimoLoginFormatada()}</span>
                            </p>
                        </li>
                        <li class="divider"></li>
                        <li><a href="usuario?acao=pfl&cdg=${sessionScope.usuarioLogado.codigo}"><span class="icon_id-2"></span> Perfil</a></li>
                        <li><a href="notificacoes?act=lst"><span class="glyphicon glyphicon-bell"></span> Notificações <span class="badge"></span></a></li>
                        <li class="divider"></li>
                        <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="icon_info"></span> Sobre</a></li>
                        <li><a href="http://www.smarties.com.br" target="_blank"><span class="icon_question"></span> Ajuda</a></li>  
                        <li class="divider"></li>
                        <li><a href="home?act=logout"><span class="glyphicon glyphicon-off"></span> Sair</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>