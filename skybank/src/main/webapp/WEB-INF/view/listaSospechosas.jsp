<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 04/05/2023
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ClienteEntity> clientesSospechosos = (List<ClienteEntity>) request.getAttribute("clientesSospechosos");
    List<EmpresaEntity> empresasSospechosas = (List<EmpresaEntity>) request.getAttribute("empresasSospechosas");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Lista de transacciones sospechosas</h1>
<table border="1">
    <thead>
    <td>CLIENTE/EMPRESA</td>
    <td>NOMBRE</td>
    <td>DNI/CIF</td>
    <td>ACTIVAR</td>
    </thead>
    <%
        if(!clientesSospechosos.isEmpty()){
            for (ClienteEntity c : clientesSospechosos){
    if(c.getBloqueado()==0){%>
    <tr>
        <td>Cliente</td>
        <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
        <td><%=c.getDni()%></td>
        <td><a href="bloquearCliente2?postId=<%=c.getIdcliente()%>">BLOQUEAR</a></td>
    </tr>
    <% }else{ %>
    <tr>
        <td>Cliente</td>
        <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
        <td><%=c.getDni()%></td>
        <td><a href="desbloquearCliente2?postId=<%=c.getIdcliente()%>">DESBLOQUEAR</a></td>
    </tr>
    <%}}}
        if(!empresasSospechosas.isEmpty()){
            for(EmpresaEntity e : empresasSospechosas){
            if(e.getBloqueada()==0){

    %>
    <tr>
        <td>Empresa</td>
        <td><%=e.getNombre()%></td>
        <td><%=e.getCif()%></td>
        <td><a href="bloquearEmpresa2?postId=<%=e.getIdempresa()%>">BLOQUEAR</a></td>
    </tr>
    <% }else{ %>
    <tr>
        <td>Empresa</td>
        <td><%=e.getNombre()%></td>
        <td><%=e.getCif()%></td>
        <td><a href="desbloquearEmpresa2?postId=<%=e.getIdempresa()%>">DESBLOQUEAR</a></td>
    </tr>
    <%}}}%>
</table>
</body>
</html>