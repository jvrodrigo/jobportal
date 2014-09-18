<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <title>JobPortal - Mis candidaturas</title>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
    </head>
    <body>
    <header><h1>Bienvenido a JobPortal!</h1></header>
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

                        <li><s:url id="viewURL" action="listCandidateInscription" namespace="/inscriptions">
                            <s:param name="id" value="%{#session.id}"></s:param>
                        </s:url>
                        <s:a href="%{viewURL}">Ver candidaturas</s:a></li>
                    <li><a href="<s:url action='LogOutAction' namespace="/"/>">Cerrar sesión</a></li>
                </ul>
            </nav>
            <main>
                <h1>Mis candidaturas</h1>
                <s:action name="listCandidateInscription" namespace="/inscriptions" var="listInscription" />
                <s:iterator value="#listInscription.listInscription" status="inscriptionStatus">
                    <table class="list">
                        <tr>
                            <th><s:url id="viewURL" action="viewJob" namespace="/jobs">
                                    <s:param name="id" value="%{jobId}"></s:param>
                                </s:url>
                                <s:a href="%{viewURL}"><s:property value="jobTitle"/></s:a></th>
                            <tr>
                                <td><s:date name="created" format="dd/MM/yyyy"/></td>
                        </tr>
                        <tr>
                            <td>Estado: <s:property value="status"/></td>
                        </tr>
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
    </s:if>
    <s:else>
        <h1>No tienes acceso a este contenido</h1>
    </s:else>
</body>
</html>
