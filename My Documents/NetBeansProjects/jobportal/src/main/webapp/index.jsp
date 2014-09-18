<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<html>
    <head>
        <title>JobPortal</title>
        <sj:head jqueryui="true"/>
        <s:head/>

        <script type="text/javascript">

            $(document).ready(function() {
                $("#pruebaAJAX").click(function() {
                    $(this).animate({margin: '+=10px'});
                });
            });

        </script>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
    <header><h1>Bienvenido a JobPortal!</h1></header>
    <div class="content">
    <nav>
        <s:if test="#session.role=='candidate'">
            menu de candidato<br>
            Bienvenido <strong><s:property value="#session.userName"/></strong>
            <ul>
                <li><s:url id="viewURL" action="viewProfile" namespace="/profiles">
                        <s:param name="id" value="%{#session.id}"></s:param>
                    </s:url>
                    <s:a href="%{viewURL}">Ver perfil</s:a></li>
                <li><s:url id="viewURL" action="viewCandidate" namespace="/candidates">
                        <s:param name="id" value="%{#session.id}"></s:param>
                    </s:url>
                    <s:a href="%{viewURL}">Ver candidato</s:a></li>
                <li><s:url id="viewURL" action="listCandidateInscription" namespace="/inscriptions">
                        <s:param name="id" value="%{#session.id}"></s:param>
                    </s:url>
                    <s:a href="%{viewURL}">Ver candidaturas</s:a></li>
                <li><a href="<s:url action='LogOutAction' namespace="/"/>">Cerrar sesión</a></li>
            </ul>
        </s:if>
        <s:elseif test="#session.role=='admin'">
            <ul>
                <li><a href="<s:url action='listCandidate' namespace="/candidates"/>">Listar candidatos</a></li>
                <li><a href="<s:url action='listCompany' namespace="/companies"/>">Listar empresas</a></li>
            </ul>
        </s:elseif>
        <s:elseif test="#session.role=='company'">
            menu de empresa<br>
            Bienvenido <strong><s:property value="#session.userName"/></strong>
            <ul>
                <li><s:url id="viewURL" action="viewCompany" namespace="/companies">
                        <s:param name="id" value="%{#session.id}"></s:param>
                    </s:url><s:a href="%{viewURL}">Ver empresa</s:a></li>
                <li><a href="<s:url action='addJob' namespace="/jobs"/>">Añadir trabajo</a></li>
                <li><s:url id="viewURL" action="jobs/listJob">
                        <s:param name="id" value="%{#session.id}"></s:param>
                    </s:url>
                    <s:a href="%{viewURL}">Ver trabajos publicados</s:a></li>
                <li><a href="<s:url action='LogOutAction' namespace="/"/>">Cerrar Sesión</a></li>
            </ul>

        </s:elseif>
        <s:else>
            <span>Bienvenido a JobPortal</span>
            <br>
            <span>Para poder optar a las <strong>ofertas de trabajo</strong> tendrás que <a href="<s:url action='addCandidate' namespace="/candidates"/>">Regístrate Candidato</a></span>
        </s:else>
    </nav>
    <main>
        <h1><s:property value="flashMessage"/></h1>
        <s:if test="#session.role=='company'">
            <s:form action="searchProfile" namespace="/search">
                <s:textfield id="searchForm" name="searchField" label="Buscar perfiles"/>
                <s:submit value="Buscar"/>
            </s:form>
        </s:if>
        <s:elseif test="#session.role=='candidate'">
            <div class="search">
            <s:form action="searchJob" namespace="/search">
                <s:textfield id="searchForm" name="searchField" label="Buscar ofertas" javascriptTooltip="Escribe aquí" required="true"></s:textfield>
                <s:submit value="Buscar" />
            </s:form>
            <a href="<s:url action='avancedSearchJob' namespace="/search"/>">Busqueda avanzada</a>
            </div>
        </s:elseif>
        <s:else>
            <div class="loggin">
                Entrar usuario
                <a href="<s:url action='addCandidate' namespace="/candidates"/>">Regístrate Candidato</a>
                <s:form id="login"  action="LogInActionCandidate" namespace="/" class="com.uva.jobportal.actions.LoginAction" method="POST">
                    <s:textfield name="userName"  label="Nombre de usuario" required="true"/>
                    <s:password id="candidatePassWord" name="candidatePassWord" type="password" label="Contraseña" required="true"/>
                    <s:submit value="Entrar"/>
                </s:form>
            </div>
            <div class="loggin">
                Entrar empresa
                <a href="<s:url action='addCompany' namespace="/companies"/>">Registrate Empresa</a>
                <s:form action="LogInActionCompany" namespace="/" class="com.uva.jobportal.actions.LoginAction" method="POST">                
                    <s:textfield name="cif" label="C.I.F. Empresa" required="true"/>
                    <s:textfield name="companyPassWord" type="password" label="Contraseña"  required="true"/>
                    <s:submit value="Entrar"/>
                </s:form>
            </div>
            <div class="search">
            <s:form action="searchJob" namespace="/search">
                <s:textfield id="searchForm" name="searchField" label="Buscar ofertas" javascriptTooltip="Escribe aquí" required="true"></s:textfield>
                <s:submit value="Buscar" />
            </s:form>
            <a href="<s:url action='avancedSearchJob' namespace="/search"/>">Busqueda avanzada</a>
            </div>
        </s:else>
        <s:div id="pruebaAJAX" name="pruebaAJAX">Esto es una prueba AJAX, pulsame para probar</s:div>
        <s:action name="listJob" namespace="/jobs" var="listJob" />
        <span class="info">Tenemos en nuestra web (<s:property value="%{#listJob.listJob.size}"/>) trabajos publicados</span>
        <h1>Últimos trabajos publicados</h1>
        <s:iterator value="#listJob.listJob" status="jobStatus">
            <table class="list">
                <tr>
                    <th>
                        <s:url id="viewURL" action="viewJob" namespace="/jobs">
                            <s:param name="id" value="%{id}"></s:param>
                        </s:url>
                        <s:a href="%{viewURL}"><s:property value="title" /></s:a>
                        </th>
                    </tr>
                    <tr>

                        <td><s:url id="viewURL" action="viewCompany" namespace="/companies">
                            <s:param name="id" value="%{companyId}"></s:param>
                        </s:url>
                        <s:a href="%{viewURL}" title="Ver más trabajos de la empresa %{companyName}">Ver empresa <s:property value="companyName" /></s:a></td>
                    </tr>
                    <tr>
                        <td><s:date name="created" format="dd/MM/yyyy"/></td>
                </tr>
                <tr>
                    <td>
                        Formación <s:property value="training"/> | 
                        Experiencia <s:property value="experience"/> | 
                        Idiomas <s:property value="languages"/>
                    </td>
                </tr>
                <s:if test="#session.role=='company'">
                    <tr>
                        <td>
                            <s:if test="#session.id==%{companyId}">
                                <s:url id="editURL" action="editJob" namespace="/jobs">
                                    <s:param name="id" value="%{id}"></s:param>
                                </s:url>
                                <s:a href="%{editURL}">Editar</s:a>
                                <s:url id="deleteURL" action="deleteJob" namespace="/jobs">
                                    <s:param name="id" value="%{id}"></s:param>
                                </s:url>
                                <s:a href="%{deleteURL}">Eliminar</s:a>
                            </s:if>
                        </td>
                    </tr>
                </s:if>
            </table>
        </s:iterator>
    </main>
    </div>
    <footer>
        Todos los derechos reservados (CC)
        <br>
        Contacta con el admistrador
        <br>
        Mapa Web
    </footer>
</body>
</html>