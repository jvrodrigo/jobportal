<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <title>JobPortal - View</title>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
    </head>
    <body>
    <header><h1>Bienvenido a JobPortal!</h1></header>
    <div class="content">
        <nav><s:if test="#session.role=='candidate'">
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
                    <li><s:a href="../index.jsp">Volver a index</s:a></li>
                    <li><s:url id="viewURL" action="viewCompany" namespace="/companies">
                            <s:param name="id" value="%{#session.id}"></s:param>
                        </s:url><s:a href="%{viewURL}">Ver empresa</s:a></li>
                    <li><a href="<s:url action='addJob' namespace="/jobs"/>">Añadir trabajo</a></li>
                    <li><s:url id="viewURL" action="jobs/listJob">
                            <s:param name="id" value="%{#session.id}"></s:param>
                        </s:url>
                        <s:a href="%{viewURL}">Ver trabajos publicados</s:a></li>
                    <li><a href="<s:url action='LogOutAction'/>">LogOut</a></li>
                </ul>

            </s:elseif>
            <s:else>
                <s:a href="../index.jsp">Volver a index</s:a>
            </s:else>
        </nav>
        <main>
            <h1><s:property value="flashMessage"/></h1>
            <h1>Ver Candidato</h1>
            <table>
                <tr>
                    <th>UserName</th>
                    <th>Email</th>
                    <th>Creado</th>
                    <th>Modificado</th>
                    <th>Último acceso</th>
                    <th>Acciones</th>
                </tr>
                <tr><s:push value="model">
                        <td><s:property value="userName" /></td>
                        <td><s:property value="email" /></td>
                        <td><s:date name="created" format="dd/MM/yyyy"/></td>
                        <td><s:date name="modified" format="dd/MM/yyyy"/></td>
                        <td><s:date name="lastLogin" format="dd/MM/yyyy/ 'a las' hh:mm:ss a"/></td>
                        <td>
                            <s:url id="editURL" action="editCandidate">
                                <s:param name="id" value="%{id}"></s:param>
                            </s:url>
                            <s:a href="%{editURL}">Editar</s:a>
                            <s:url id="deleteURL" action="deleteCandidate">
                                <s:param name="id" value="%{id}"></s:param>
                            </s:url>
                            <s:a href="%{deleteURL}">Eliminar</s:a>
                            </td>
                    </s:push>
                </tr>
            </table>
            <h1>Mis candidaturas(Inscripciones)</h1>
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
                        <td><s:property value="status"/></td>
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
</body>
</html>
