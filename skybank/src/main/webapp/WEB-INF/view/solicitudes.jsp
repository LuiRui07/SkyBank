<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.dto.Empresa" %>
<%@ page import="com.example.skybank.dto.Cliente" %>
<%@ page import="com.example.skybank.dto.Socio" %><%--
  Created by IntelliJ IDEA.
  User: Rafael Ceballos
  Date: 03/05/2023
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Empresa> solicitadasE =(List<Empresa>) request.getAttribute("solicitadasE");
    List<Cliente> solicitadas = (List<Cliente>) request.getAttribute("solicitadas");
    List<Cliente> solicitaReactivacion = (List<Cliente>) request.getAttribute("solicitudesReactivar");
    List<Socio> solicitaDesbloqueo = (List<Socio>) request.getAttribute("socioDesbloqueo");
%>
<html>
<head>
    <title>SOLICITUDES</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<div class="container mt-4">
    <div class="container" style="margin-bottom: 4%">
        <h1 class="display-3 mt-5">Lista de solicitudes</h1>
    </div>
    <div class="container">
        <h2>SOLICITUDES DE ALTA</h2>
    </div>
    <table class="table">
        <thead>
        <th scope="col">CLIENTE/EMPRESA</th>
        <th scope="col">NOMBRE</th>
        <th scope="col">DNI/CIF</th>
        <th scope="col">ACTIVAR</th>
        <th scope="col">RECHAZAR</th>
        </thead>
        <% for (Cliente c:solicitadas){ %>
        <tr>
            <td>Cliente</td>
            <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
            <td><%=c.getDni()%></td>
            <td><a href="aceptarCliente?postId=<%=c.getIdcliente()%>">Aceptar</a></td>
            <td><a href="rechazarCliente?postId=<%=c.getIdcliente()%>">Rechazar</a> </td>
        </tr>
        <% }
            for(Empresa e :solicitadasE){
        %>
        <tr>
            <td>Empresa</td>
            <td><%=e.getNombre()%></td>
            <td><%=e.getCif()%></td>
            <td><a href="aceptarEmpresa?postId=<%=e.getIdempresa()%>">Aceptar</a></td>
            <td><a href="rechazarEmpresa?postId=<%=e.getIdempresa()%>">Rechazar</a> </td>
        </tr>
        <% }%>
    </table>

    <div class="container" >
        <h2>SOLICITUDES REACTIVACION DE CLIENTES</h2>
    </div>
    <table class="table">
        <thead>
        <th scope="col">NOMBRE</th>
        <th scope="col">DNI/CIF</th>
        <th scope="col">ACTIVAR</th>
        </thead>
        <% if(solicitaReactivacion!=null){
            for (Cliente c: solicitaReactivacion){ %>
        <tr>
            <td scope="row"><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
            <td><%=c.getDni()%></td>
            <td><a href="reactivarCliente?postId=<%=c.getIdcliente()%>">Reactivar</a></td>
        </tr>
        <% }
        } %>
    </table>
    <div class="container" >
        <h2>SOLICITUDES DESBLOQUEO DE SOCIOS</h2>
    </div>

    <table class="table">
        <thead>
        <th scope="col">NOMBRE</th>
        <th scope="col">DNI/CIF</th>
        <th scope="col">DESBLOQUEAR</th>
        </thead>
        <% if(solicitaDesbloqueo!=null){
            for (Socio s: solicitaDesbloqueo){ %>
        <tr>
            <% if(s.getApellido2()!=null){%>
            <td  scope="row"><%=s.getNombre()%> <%=s.getApellido1()%> <%=s.getApellido2()%></td>
            <%} else{ %>
            <td  scope="row"><%=s.getNombre()%> <%=s.getApellido1()%></td>
            <% } %>
            <td><%=s.getNif()%></td>
            <td><a href="desbloquearSocio?postId=<%=s.getId()%>">Desbloquear</a></td>
        </tr>
        <% }
        } %>
    </table>
    <br>
    <a  class="btn btn-danger" href="/gestor/">VOLVER</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>