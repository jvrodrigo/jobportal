<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <title>JobPortal - View</title>
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
                <s:a href="../index.jsp">Volver a index</s:a>
            </s:else>
        </nav>
        <main>
            <h1><s:property value="flashMessage"/></h1>
            <h1>Ver Trabajo</h1>
            <table>
                <s:push value="model">
                    <tr>
                        <th>Título</th>
                        <td><s:property value="title" /></td>
                    </tr>
                    <tr>
                        <th>Localización</th>
                        <td><s:property value="location" /></td>
                    </tr>
                    <tr>
                        <th>Categoría</th>
                        <td><s:property value="category" /></td>
                    </tr>
                    <tr>
                        <th>Descripción</th>
                        <td><s:property value="description" /></td>
                    </tr>
                    <tr>
                        <th>Formación</th>
                        <td><s:property value="training" /></td>
                    </tr>
                    <tr>
                        <th>Experiencia</th>
                        <td><s:property value="experience" /></td>
                    </tr>
                    <tr>
                        <th>Idiomas</th>
                        <td><s:property value="languages" /></td>
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
                </s:push>
            </table>
            <s:if test="#session.role=='candidate'">
                <s:if test="isInscribed==false">
                    <s:form action="addInscription" namespace="/inscriptions" class="com.uva.jobportal.web.InscriptionAction" method="POST">
                        <s:textfield value="%{#session.id}" name="candidateId" type="hidden"/>
                        <s:textfield value="%{#session.userName}" name="candidateName" type="hidden"/>
                        <s:textfield value="%{model.id}" name="jobId" type="hidden"/>
                        <s:textfield value="%{model.title}" name="jobTitle" type="hidden"/>
                        <s:submit value="Inscribirme" />
                    </s:form>
                </s:if>
                <s:else>
                    <p>Ya estas inscrito en esta oferta de empleo</p>
                </s:else>
            </s:if>
            <s:elseif test="%{#session.role=='company'}">
                <s:set name="companyId" value="%{model.companyId}"/>
                <s:if test="%{#companyId==#session.id}">
                    <s:action name="listJobInscription" namespace="/inscriptions" var="listInscription" />
                    <s:if test="%{#listInscription.listInscription.size==0}"><h1>No hay candidatos inscritos a esta oferta</h1></s:if>
                    <s:else>
                        <h1>Candidatos inscritos</h1>
                        <table>
                            <tr>
                                <th>Nombre del candidato</th>
                                <th>Inscrito el día</th>
                                <th>Acciones</th>
                                <th>Estado de la candidatura</th>
                            </tr>
                            <s:iterator value="#listInscription.listInscription" status="inscriptionStatus">
                                <tr>                   
                                    <td>
                                        <s:url id="viewURL" action="viewProfile" namespace="/profiles">
                                            <s:param name="id" value="%{candidateId}"></s:param>
                                        </s:url>
                                        <s:a href="%{viewURL}" title="Ver candidato %{candidateName}"><s:property value="%{candidateName}"/></s:a>
                                        </td>
                                        <td><s:date name="created" format="dd/MM/yyyy"/></td>
                                    <td>
                                        <s:form action="updateInscription" namespace="/inscriptions">
                                            <s:textfield value="DESCARTADO" name="status" type="hidden"/>
                                            <s:textfield value="%{id}" name="id" type="hidden"/>
                                            <s:submit value="descartar"/>
                                        </s:form>
                                        <s:form action="updateInscription" namespace="/inscriptions">
                                            <s:textfield value="SELECCIONADO" name="status" type="hidden"/>
                                            <s:textfield value="%{id}" name="id" type="hidden"/>
                                            <s:submit value="seleccionar"/>
                                        </s:form>
                                    </td>
                                    <td>
                                        <s:property value="%{status}"/>
                                    </td>
                                </tr>

                            </s:iterator>
                        </table>
                    </s:else>
                </s:if>
            </s:elseif>
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
