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
</head>
<body>
<h1>Lista de solicitudes</h1>
<h2>SOLICITUDES ALTA</h2>
    <table border="1">
        <thead>
        <td>CLIENTE/EMPRESA</td>
        <td>NOMBRE</td>
        <td>DNI/CIF</td>
        <td>ACTIVAR</td>
        </thead>
        <% for (Cliente c:solicitadas){ %>
        <tr>
            <td>Cliente</td>
            <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
            <td><%=c.getDni()%></td>
            <td><a href="aceptarCliente?postId=<%=c.getIdcliente()%>">Aceptar</a></td>
        </tr>
        <% }
        for(Empresa e :solicitadasE){
        %>
        <tr>
            <td>Empresa</td>
            <td><%=e.getNombre()%></td>
            <td><%=e.getCif()%></td>
            <td><a href="aceptarEmpresa?postId=<%=e.getIdempresa()%>">Aceptar</a></td>
        </tr>
        <% }%>
    </table>

<h2>SOLICITUDES REACTIVACION DE CLIENTES</h2>
<table border="1">
    <thead>
    <td>NOMBRE</td>
    <td>DNI/CIF</td>
    <td>ACTIVAR</td>
    </thead>
        <% if(solicitaReactivacion!=null){
            for (Cliente c: solicitaReactivacion){ %>
    <tr>
        <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
        <td><%=c.getDni()%></td>
        <td><a href="reactivarCliente?postId=<%=c.getIdcliente()%>">Reactivar</a></td>
    </tr>
        <% }
        } %>
</table>

<h2>SOLICITUDES DESBLOQUEO DE SOCIOS</h2>

<table border="1">
    <thead>
    <td>NOMBRE</td>
    <td>DNI/CIF</td>
    <td>DESBLOQUEAR</td>
    </thead>
    <% if(solicitaDesbloqueo!=null){
        for (Socio s: solicitaDesbloqueo){ %>
    <tr>
        <td><%=s.getNombre()%> <%=s.getApellido1()%> <%=s.getApellido2()%></td>
        <td><%=s.getNif()%></td>
        <td><a href="desbloquearSocio?postId=<%=s.getId()%>">Desbloquear</a></td>
    </tr>
    <% }
    } %>
</table>
<br>
<a href="/gestor/">VOLVER</a>
</body>
</html>