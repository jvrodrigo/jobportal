<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <title>JobPortal - Error Search</title>
    </head>
    <body>
        <div class="content">
            <nav>
                <s:if test="#session.role=='candidate'">
                    menu de candidatos<br>
                    Bienvenido <strong><s:property value="#session.userName"/></strong>
                    <ul>
                        <li><s:a href="../index.jsp">Volver a index</s:a></li>
                        <li><s:url id="viewURL" action="viewProfile" namespace="/profiles">
                                <s:param name="id" value="%{#session.id}"></s:param>
                            </s:url>
                            <s:a href="%{viewURL}">Ver perfil</s:a></li>
                        <li><s:url id="viewURL" action="viewCandidate" namespace="/candidates">
                                <s:param name="id" value="%{#session.id}"></s:param>
                            </s:url>
                            <s:a href="%{viewURL}">Ver candidato</s:a></li>
                        <li><a href="<s:url action='LogOutAction' namespace="/"/>">Cerrar Sesión</a></li>
                    </ul>
                </s:if>
                <s:elseif test="#session.role=='admin'">
                    <ul>
                        <li><s:a href="../index.jsp">Volver a index</s:a></li>
                        <li><a href="<s:url action='listCandidate' namespace="/candidates"/>">Listar candidatos</a></li>
                        <li><a href="<s:url action='listCompany' namespace="/companies"/>">Listar empresas</a></li>
                    </ul>
                </s:elseif>
                <s:elseif test="#session.role=='company'">
                    menu de empresa<br>
                    Bienvenido <strong><s:property value="#session.userName"/></strong>
                    <ul>
                        <li><s:a href="../index.jsp">Volver a index</s:a></li>
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
                    <s:a href="index.jsp">Volver a index</s:a>
                </s:else>
            </nav>
            <main>
                <h1><s:property value="flashMessage"/></h1>
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
