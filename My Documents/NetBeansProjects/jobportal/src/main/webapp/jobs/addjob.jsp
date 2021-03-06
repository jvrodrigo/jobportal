<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<head>
    <s:head/>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <title>JobPortal - Añadir trabajo</title>
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
            <s:if test="#session.role=='company'">
                <h1>Añadir trabajo</h1>
                <s:form action="addJob" class="com.uva.jobportal.jobs.AddJob">
                    <s:textfield name="title" label="Título" />
                    <s:textarea rows="10" cols="30" name="description" label="Descripción" />
                    <s:select list="listCategory" name="category" multiple="true" label="Categoria" />
                    <s:select list="listLocation" name="location" multiple="true" label="Provincia / País" />
                    <s:select list="listTraining" name="training" label="Formación" />
                    <s:select list="listExperience" name="experience" label="Experiencia" />
                    <s:checkboxlist list="listLanguages" name="languages" label="Idiomas" />
                    <s:textfield name="companyName" value="%{#session.userName}" type="hidden" />
                    <s:textfield name="companyId" value="%{#session.id}" type="hidden" />
                    <s:submit value="Guardar" />
                </s:form>
            </s:if><s:else>
                <h1>No tienes acceso a este contenido</h1>
            </s:else>
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
