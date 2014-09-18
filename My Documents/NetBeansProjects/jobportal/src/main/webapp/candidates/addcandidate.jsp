<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <s:head/>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <title>JobPortal - Añadir candidato</title>
    </head>
    <body>
    <header><h1>Bienvenido a JobPortal!</h1></header>
    <div class="content">
        <nav>
            <s:a href="../index.jsp">Volver a index</s:a>
            </nav>
            <main>
                <h1>Registrar candidato</h1>
                <h1><s:property value="flashMessage"/></h1>
            <s:form action="addCandidate" class="com.uva.jobportal.web.CandidateAction">
                <s:textfield name="userName"  label="Nombre de usuario" />
                <s:textfield name="passWord" type="password" label="Contraseña" />
                <s:textfield name="passWordConf" type="password" label="Repita la contraseña" />
                <s:textfield name="email" label="Email" />
                <s:submit label="Guardar" />
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