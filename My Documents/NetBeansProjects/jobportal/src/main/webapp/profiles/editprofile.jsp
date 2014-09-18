<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <s:head/>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <title>JobPortal - Editar el perfil</title>
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
            </s:if>
        </nav>
        <main>
            <h1><s:property value="flashMessage"/></h1>
            <h1>Editar Perfil</h1>
            <s:form action="editProfile" class="com.uva.jobportal.web.ProfileAction">
                <s:textfield value="%{model.id}" name="id" type="hidden"/>
                <s:textfield name="name" value="%{model.name}" label="Nombre" />
                <s:textfield name="surName" value="%{model.surName}" label="Apellidos" />
                <s:textarea cols="40" rows="20" name="curriculum" value="%{model.curriculum}" label="Currículum" />
                <s:select list="listCategory" name="category" multiple="true" value="%{model.category}" label="Categoria" />
                <s:select list="listLocation" name="location" multiple="true" value="%{model.location}" label="Provincia / País" />
                <s:select list="listTraining" name="training" value="%{model.training}" label="Formación" />
                <s:select list="listExperience" name="experience" value="%{model.experience}" label="Experiencia" />
                <s:checkboxlist list="listLanguages" name="languages" value="%{model.languages}" label="Idiomas" />
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
