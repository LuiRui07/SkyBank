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
    <thead>
        <td>CLIENTES</td>
        <td>EMPRESAS</td>
    </thead>
    <tr>
        <td>
            <table>
                <%
                    for(ClienteEntity c : listaClientes){
                %>
                <tr>
                    <td> <a href="/gestor/gestionarCliente?postId="<%=c.getIdcliente()%>"><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></a></td>

                </tr>
                <% } %>
            </table>
        </td>
        <td>
            <table>
                <%
                    for(EmpresaEntity e : listaEmpresas){
                %>
                <tr>
                    <td> <a href="/gestor/gestionarEmpresa?postId="<%=e.getIdempresa()%>"><%=e.getNombre()%></a>  </td>
                </tr>
                <% } %>
            </table>
        </td>

    </tr>
</table>

<a class="btn btn-primary" href="solicitudes"> Ver Solicitudes </a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
