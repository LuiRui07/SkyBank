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
                <th></th>
                <th></th>
            </tr>
        <%
            for (SocioEntity s : sociosEmpresa){
        %>
            <tr >
                <td><%=s.getNombre()%> <%=s.getApellido1()%> <%=s.getApellido2() == null ? "" : s.getApellido2()%></td>
                <td><%=s.getNif()%></td>
                <td>Socio</td>
                <td> <a href="/empresa/socios/bloquear?id" + <%=s.getId()%>></a></td>
                <td> <a href="/empresa/socios/borrar?id" + <%=s.getId()%>></a></td>
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
            <td> <a href="/empresa/autorizados/bloquear?id" + <%=a.getId()%>></a></td>
            <td> <a href="/empresa/autorizados/borrar?id" + <%=a.getId()%>></a></td>
        </tr>
        <%
            }
        %>
        </table>

        <button class="btn btn-outline-danger m-auto" id="addSocio">
            Añadir una nueva persona
        </button>
    </div>


       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>
