<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.dto.Empresa" %>
<%@ page import="com.example.skybank.dto.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 04/05/2023
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cliente> clientesObsoletos = (List<Cliente>) request.getAttribute("listaClientesObsoletos");
    List<Empresa> empresasObsoletas = (List<Empresa>) request.getAttribute("listaEmpresasObsoletas");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Listado Cuentas en Desuso</h1>
<table border="1">
    <thead>
    <td>CLIENTE/EMPRESA</td>
    <td>NOMBRE</td>
    <td>DNI/CIF</td>
    <td>ACTIVAR</td>
    </thead>
    <%
        if(!clientesObsoletos.isEmpty()){
        for (Cliente c : clientesObsoletos){
            if(c.getBloqueado() == 0){
    %>
    <tr>
        <td>Cliente</td>
        <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
        <td><%=c.getDni()%></td>
        <td><a href="bloquearCliente?postId=<%=c.getIdcliente()%>">BLOQUEAR</a></td>
    </tr>
    <% } else { %>
    <tr>
        <td>Cliente</td>
        <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
        <td><%=c.getDni()%></td>
        <td><a href="desbloquearCliente?postId=<%=c.getIdcliente()%>">DESBLOQUEAR</a></td>
    </tr>
    <% }}}
    if(!empresasObsoletas.isEmpty()){
        for(Empresa e : empresasObsoletas){
            if(e.getBloqueada() == 0){
    %>
    <tr>
        <td>Empresa</td>
        <td><%=e.getNombre()%></td>
        <td><%=e.getCif()%></td>
        <td><a href="bloquearEmpresa?postId=<%=e.getIdempresa()%>">BLOQUEAR</a></td>
    </tr>
    <% } else { %>
    <tr>
        <td>Empresa</td>
        <td><%=e.getNombre()%></td>
        <td><%=e.getCif()%></td>
        <td><a href="desbloquearEmpresa?postId=<%=e.getIdempresa()%>">DESBLOQUEAR</a></td>
    </tr>
    <%}}} %>
</table>
<br>
<a href="/gestor/">VOLVER</a>
</body>
</html>