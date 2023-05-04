<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.SocioEntity" %><%--
  Created by IntelliJ IDEA.
  User: Rafael Ceballos
  Date: 03/05/2023
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<EmpresaEntity> solicitadasE =(List<EmpresaEntity>) request.getAttribute("solicitadasE");
    List<ClienteEntity> solicitadas = (List<ClienteEntity>) request.getAttribute("solicitadas");
    List<ClienteEntity> solicitaReactivacion = (List<ClienteEntity>) request.getAttribute("solicitudesReactivar");
    List<SocioEntity> solicitaDesbloqueo = (List<SocioEntity>) request.getAttribute("socioDesbloqueo");
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
        <% for (ClienteEntity c:solicitadas){ %>
        <tr>
            <td>Cliente</td>
            <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
            <td><%=c.getDni()%></td>
            <td><a href="aceptarCliente?postId=<%=c.getIdcliente()%>">Aceptar</a></td>
        </tr>
        <% }
        for(EmpresaEntity e :solicitadasE){
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
            for (ClienteEntity c: solicitaReactivacion){ %>
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
    <td>ACTIVAR</td>
    </thead>
    <% if(solicitaDesbloqueo!=null){
        for (SocioEntity s: solicitaDesbloqueo){ %>
    <tr>
        <td><%=s.getNombre()%> <%=s.getApellido1()%> <%=s.getApellido2()%></td>
        <td><%=s.getNif()%></td>
        <td><a href="desbloquearSocio?postId=<%=s.getId()%>">DESBLOQUEAR</a></td>
    </tr>
    <% }
    } %>
</table>

<a href="/gestor/">VOLVER</a>
</body>
</html>