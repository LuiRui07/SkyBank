<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.GestorEntity" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 03/05/2023
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> listaClientes = (List<ClienteEntity>) request.getAttribute("listaClientes");
    List<EmpresaEntity> listaEmpresas = (List<EmpresaEntity>) request.getAttribute("listaEmpresas");
    GestorEntity gestor = (GestorEntity) request.getAttribute("gestor");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>LISTADO CLIENTES</h1>
<table border="1">
    <th>CLIENTES</th>
    <%
        for(ClienteEntity c : listaClientes){
    %>
    <tr>
        <td> <%=c.getNombre()%> </td>
    </tr>
    <% } %>
    <th>EMPRESAS</th>
    <%
        for(EmpresaEntity e : listaEmpresas){
    %>
    <tr>
        <td> <%=e.getNombre()%> </td>
    </tr>
    <% } %>
</table>

</body>
</html>
