<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.dto.Cliente" %>
<%@ page import="com.example.skybank.dto.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 04/05/2023
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Cliente> clientesSospechosos = (List<Cliente>) request.getAttribute("clientesSospechosos");
    List<Empresa> empresasSospechosas = (List<Empresa>) request.getAttribute("empresasSospechosas");
%>
<html>
<head>
    <title>Sospechosas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h1 class="display-3 mt-5">Lista de transacciones sospechosas</h1>
    <table class="table">
        <thead>
        <th scope="col">CLIENTE/EMPRESA</th>
        <th scope="col">NOMBRE</th>
        <th scope="col">DNI/CIF</th>
        <th scope="col">ACTIVAR</th>
        </thead>
        <tbody>
        <%
            if(!clientesSospechosos.isEmpty()){
                for (Cliente c : clientesSospechosos){
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
        <%}}} if(!empresasSospechosas.isEmpty()){
            for(Empresa e : empresasSospechosas){
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
        </tbody>
    </table>
    <br>
    <a href="/gestor/" class="btn btn-danger">VOLVER</a>
</div>

</body>
</html>