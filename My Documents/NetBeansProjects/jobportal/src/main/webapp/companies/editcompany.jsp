<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <s:head/>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <title>JobPortal - Editar la empresa</title>
    </head>
    <body>
    <header><h1>Bienvenido a JobPortal!</h1></header>
    <div class="content">
        <nav>
            <s:if test="#session.role=='company'">
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
            </s:if>
            <s:else>
                <s:a href="../index.jsp">Volver a index</s:a>
            </s:else>
        </nav>
        <main>
            <h1><s:property value="flashMessage"/></h1>
            <h1>Editar la Empresa</h1>
            <s:form action="editCompany" class="com.uva.jobportal.web.CompanyAction">
                <s:textfield value="%{model.id}" name="id" type="hidden"/>
                <s:textfield name="passWord" type="password" label="Contraseña" />
                <s:textfield name="passWordConf" type="password" label="Repita la contraseña" />
                <s:textfield value="%{model.email}" name="email" label="Email" />
                <s:submit value="Guardar" />
               
            </s:form>
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
