<%-- 
    Document   : error
    Created on : Apr 16, 2014, 8:43:59 PM
    Author     : Administrator
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <title>JobPortal - Companies Error</title>
    </head>
    <body>
        <header><h1>Bienvenido a JobPortal!</h1></header>
        <div class="content">
            <nav>
                <s:a href="../index.jsp">Volver a index</s:a>
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
