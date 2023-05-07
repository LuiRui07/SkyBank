<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.dto.Empresa" %>
<%@ page import="com.example.skybank.dto.Cliente" %>
<%@ page import="com.example.skybank.dto.Gestor" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 03/05/2023
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
    List<Empresa> listaEmpresas = (List<Empresa>) request.getAttribute("listaEmpresas");
    Gestor gestor = (Gestor) request.getAttribute("gestor");
%>
<html>
<head>
    <title>SkyBank-gestor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container">

    <div class="container" style="margin-bottom: 4%">
        <h1 class="display-3 mt-5">LISTADO CLIENTES Y EMPRESAS</h1>
    </div>

    <div class="ml3">
        <form:form modelAttribute="filtro" method="post" action="/gestor/" cssStyle="display: flex; align-items: center; gap: 50px;">
            <div style="display: flex; align-items: center; gap: 20px;">
                <label for="texto">Nombre o DNI: </label>
                <form:input path="texto" type="text" cssClass="form-control" cssStyle="max-width: 200px !important;"></form:input>
            </div>
            <div class="form-check form-switch">
                <form:checkbox path="empresas" class="form-check-input"  role="switch" id="flexSwitchCheckDefault"/>
                <label class="form-check-label" for="flexSwitchCheckDefault">Empresas</label>
            </div>
            <div class="form-check form-switch">
                <form:checkbox path="clientes" class="form-check-input"  role="switch" id="flexSwitchCheckDefault"/>
                <label class="form-check-label" for="flexSwitchCheckDefault">Clientes</label>
            </div>
            <form:button class="btn btn-warning">Filtrar</form:button>
        </form:form>
    </div>


    <table class="table ml-3 mr-3" >
        <thead>
        <th scope="col">CLIENTES</th>
        <th scope="col">EMPRESAS</th>
        </thead>
        <tr>
            <td>
                <table class="table">
                    <%
                        for(Cliente c : listaClientes){
                    %>
                    <tr>
                        <td> <a href="/gestor/gestionarCliente?postId=<%=c.getIdcliente()%>"><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></a></td>
                    </tr>
                    <% } %>
                </table>
            </td>
            <td>
                <table class="table">
                    <%
                        for(Empresa e : listaEmpresas){
                    %>
                    <tr>
                        <td> <a href="/gestor/gestionarEmpresa?postId=<%=e.getIdempresa()%>"><%=e.getNombre()%></a>  </td>
                    </tr>
                    <% } %>
                </table>
            </td>

        </tr>
    </table>
    <br>
    <div style="margin-left: 30%">
        <a class="btn btn-primary" href="solicitudes"> Ver Solicitudes </a>

        <a class="btn btn-primary" href="cuentasSinUso">Gestionar Cuentas en desuso</a>

        <a class="btn btn-primary" href="sospechas">Ver transacciones sospechosas</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
