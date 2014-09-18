<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <title>JobPortal</title>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
    </head>
    <body>
        <s:a href="../index.jsp">Volver a index</s:a>
        <h1><s:property value="flashMessage"/></h1>
        <h1>Listar Candidatos</h1>
        <table>
            <tr>
                <th>UserName</th>
                <th>Email</th>
                <th>Creado</th>
                <th>Modificado</th>
                <th>Ver</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            <s:iterator value="listCandidate" status="candidateStatus">
                <tr>
                    <td><s:property value="userName" /></td>
                    <td><s:property value="email" /></td>
                    <td><s:date name="created" format="dd/MM/yyyy"/></td>
                    <td><s:date name="modified" format="dd/MM/yyyy"/></td>
                    <td>
                        <s:url id="viewURL" action="viewCandidate">
                            <s:param name="id" value="%{id}"></s:param>
                        </s:url>
                        <s:a href="%{viewURL}">Ver</s:a>
                        </td>
                        <td>
                        <s:url id="editURL" action="editCandidate">
                            <s:param name="id" value="%{id}"></s:param>
                        </s:url>
                        <s:a href="%{editURL}">Editar</s:a>
                        </td>
                        <td>
                        <s:url id="deleteURL" action="deleteCandidate">
                            <s:param name="id" value="%{id}"></s:param>
                        </s:url>
                        <s:a href="%{deleteURL}">Eliminar</s:a>
                        </td>
                    </tr>
            </s:iterator>
        </table>
    </body>
</html>
