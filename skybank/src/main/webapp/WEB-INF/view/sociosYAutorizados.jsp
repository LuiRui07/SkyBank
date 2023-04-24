<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.SocioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.AutorizadoEntity" %><%--
  Created by IntelliJ IDEA.
  User: Pepe
  Date: 23/04/2023
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
        EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");
        List<SocioEntity> sociosEmpresa = (List<SocioEntity>) request.getAttribute("socios");
        List<AutorizadoEntity> autorizadosEmpresa = (List<AutorizadoEntity>) request.getAttribute("autorizados");

%>

<html>
<head>
    <title>Socios y Autorizados</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="container">
       <%
            if (sociosEmpresa.size() == 0) {
       %>

        <div class="alert alert-danger container mt-4" role="alert">
            La empresa no tiene ningun socio.
            <br>
            <a href="/empresa/socios/#addSocio" style="color: darkred; font-weight: bold;">[+] Añadir nuevo socio</a>
        </div>

       <%
            }
        %>

        <h2>Socios y autorizados de la empresa:</h2>
        <table class="table">
            <tr >
                <th scope="col">Nombre y Apellidos</th>
                <th scope="col">NIF</th>
                <th scope="col">Tipo</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
        <%
            for (SocioEntity s : sociosEmpresa){
        %>
            <tr >
                <td><%=s.getNombre()%> <%=s.getApellido1()%> <%=s.getApellido2() == null ? "" : s.getApellido2()%></td>
                <td><%=s.getNif()%></td>
                <td>Socio</td>
                <td> <a href="/empresa/socios/bloquear?id=<%=s.getId()%>" class="btn btn-warning">Bloquear</a></td>
                <td> <a href="/empresa/socios/borrar?id=<%=s.getId()%>" class="btn btn-danger">Eliminar</a></td>
            </tr>
        <%
            }
        %>

        <%
            for (AutorizadoEntity a : autorizadosEmpresa){
        %>
        <tr>
            <td><%=a.getNombre()%></td>
            <td><%=a.getNif()%></td>
            <td>Autorizado</td>
            <td> <a href="/empresa/autorizados/bloquear?id=<%=a.getId()%>" class="btn btn-warning">Bloquear</a></td>
            <td> <a href="/empresa/autorizados/borrar?id=<%=a.getId()%>" class="btn btn-danger">Eliminar</a></td>
        </tr>
        <%
            }
        %>
        </table>

        <p>
            <button class="btn btn-outline-danger" type="button" data-bs-toggle="collapse" data-bs-target="#collapseWidthExample" aria-expanded="false" aria-controls="collapseWidthExample" id="addSocio">
                Añadir una nueva persona
            </button>
        </p>
        <div style="min-height: 120px;">
            <div class="collapse" id="collapseWidthExample">
                <form:form modelAttribute="NuevoSocioOAutorizado" cssClass="rounded bg-light p-4" action="/empresa/socios/addSocioOrAutorizado?id=${empresa.getIdEmpresa()}" method="post">
                    <form:select path="tipo" cssClass="form-control">
                        <form:option value="Socio">Socio</form:option>
                        <form:option value="Autorizado">Autorizado</form:option>
                    </form:select>
                    <div class="mt-3">
                        <h2> Datos:</h2>
                        <div>
                            <span>NIF (*):</span>
                            <form:input path="nif" cssClass="form-control" required="required"></form:input>
                        </div>

                        <div>
                            <span>Nombre (*):</span>
                            <form:input path="nombre" cssClass="form-control" required="required"></form:input>
                        </div>

                        <div>
                            <span>Primer Apellido (*):</span>
                            <form:input path="apellido1" cssClass="form-control" required="required"></form:input>
                        </div>

                        <div>
                            <span>Segundo Apellido:</span>
                            <form:input path="apellido2" cssClass="form-control"></form:input>
                        </div>

                        <div>
                            <span>Email:</span>
                            <form:input path="email" cssClass="form-control" required="required"></form:input>
                        </div>

                        <div>
                            <span>Fecha de nacimiento:</span>
                            <form:input path="fechanacimiento" type="date" cssClass="form-control" required="required"></form:input>
                        </div>


                        <br>

                        <div style="margin-top: 10px; align-items: center; width: 20%;">
                            <span>Contraseña:</span>
                            <form:input type="password" path="password" cssClass="form-control" required="required"></form:input>
                            <br>
                        <div>

                        <div style="width: max-content;">
                            <h2>Dirección</h2>
                            <div style="display: flex; gap: 10px; width: 100%;">
                                <div>
                                    <span>Calle(*):</span>
                                    <form:input path="calle" cssClass="form-control" required="required"></form:input>
                                </div>

                                <div>
                                    <span>Número(*):</span>
                                    <form:input type="number" path="numero" cssClass="form-control" required="required"></form:input>
                                </div>

                                <div>
                                    <span>Planta/Puerta/Oficina (*):</span>
                                    <form:input type="number" path="planta" cssClass="form-control" required="required"></form:input>
                                </div>
                            </div>


                            <div style="display: flex; gap: 10px;">
                                <div>
                                    <span>Ciudad(*):</span>
                                    <form:input path="ciudad" cssClass="form-control" required="required"></form:input>
                                </div>

                                <div>
                                    <span>Región:</span>
                                    <form:input path="region" cssClass="form-control"></form:input>

                                </div>
                            </div>

                            <div style="display: flex; gap: 10px;">
                                <div>
                                    <span>País(*):</span>
                                    <form:input path="pais" cssClass="form-control" required="required"></form:input>
                                </div>

                                <div>
                                    <span>C.P.(*):</span>
                                    <form:input path="cp" cssClass="form-control" required="required"></form:input>

                                </div>
                            </div>
                        </div>

                    <br>

                    <form:button style="margin-bottom:10px;" class="btn btn-outline-danger">Registrar</form:button>

                </form:form>
            </div>
        </div>

    </div>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
