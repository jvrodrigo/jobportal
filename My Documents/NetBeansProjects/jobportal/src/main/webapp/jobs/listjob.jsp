<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <title>JobPortal</title>
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
                        <li><s:url id="viewURL" action="listJob" namespace="/jobs">
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
                <h1>Listar Trabajos</h1>
                <s:iterator value="listJob" status="jobStatus">
                    <table class="list">
                        <tr>
                            <th>Nombre del trabajo</th>
                            <td><s:url id="viewURL" action="viewJob">
                                    <s:param name="id" value="%{id}"></s:param>
                                </s:url>
                                <s:a href="%{viewURL}"><s:property value="title" /></s:a>
                                </td>
                            </tr>
                            <tr>
                                <th>Empresa</th>
                                <td><s:url id="viewURL" action="viewCompany" namespace="/companies">
                                    <s:param name="id" value="%{companyId}"></s:param>
                                </s:url>
                                <s:a href="%{viewURL}"><s:property value="companyName" /></s:a></td>
                            </tr>
                            <tr>
                                <th>Descripción</th>
                                <td><s:property value="description" /></td>
                        </tr>
                        <tr>
                            <th>Creado</th>
                            <td><s:date name="created" format="dd/MM/yyyy"/></td>
                        </tr>
                        <s:if test="#session.role=='company'">
                            <tr>
                                <th>Acciones</th>
                                <td>
                                    <s:url id="editURL" action="editJob">
                                        <s:param name="id" value="%{id}"></s:param>
                                    </s:url>
                                    <s:a href="%{editURL}">Editar</s:a>
                                    <s:url id="deleteURL" action="deleteJob">
                                        <s:param name="id" value="%{id}"></s:param>
                                    </s:url>
                                    <s:a href="%{deleteURL}">Eliminar</s:a>
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

