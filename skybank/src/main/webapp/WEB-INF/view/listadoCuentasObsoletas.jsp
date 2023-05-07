<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.dto.Empresa" %>
<%@ page import="com.example.skybank.dto.Cliente" %><%--
  Created by IntelliJ IDEA.
  Rafael Ceballos
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cliente> clientesObsoletos = (List<Cliente>) request.getAttribute("listaClientesObsoletos");
    List<Empresa> empresasObsoletas = (List<Empresa>) request.getAttribute("listaEmpresasObsoletas");
%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container mt-4">
<h1 class="display-3 mt-5">Listado Cuentas en Desuso</h1>
<table class="table">
    <thead>
    <th scope="col">CLIENTE/EMPRESA</th>
    <th scope="col">NOMBRE</th>
    <th scope="col">DNI/CIF</th>
    <th scope="col">ACTIVAR</th>
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
<a href="/gestor/" class="btn btn-danger">VOLVER</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>