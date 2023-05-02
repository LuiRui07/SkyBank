<%--
  @author: Luis Ruiz Nuñez
--%>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    List<CuentaEntity> cuentas = (List<CuentaEntity>) request.getAttribute("cuentas");
%>

<html>
<head>
    <title>Datos del Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<div class="container">
    <br/>
    <script>
        function CerrarSesion() {
        var result = confirm("¿Seguro que quieres cerrar sesión?");
        if (result == true) {
            window.location = "/cliente/logout"
        }
        }
    </script>

    <p class="h1"><%=cliente.getNombre()%>  <%=cliente.getApellido1()%> <%=cliente.getApellido2()%>
        <div>
            <a href="editar?id=${cliente.idcliente}" class="btn btn-outline-primary">Modificar Datos</a>
            <a onclick="CerrarSesion()" style="float:right" class="btn btn-outline-info"> Cerrar Sesión </a>
        </div>
    </p>

    <% for (CuentaEntity cuenta : cuentas) { %>
        <%if (!((cuenta.getSaldo() - 0.01) < 0 )){%>
        <div class="card" style="margin-bottom: 3%">
            <p class="display-3 p-3 rounded bg-light"><%=String.format("%.2f",cuenta.getSaldo())    %>     <%=cuenta.getDivisaByDivisa().getSimbolo()%>
            <%if (cuenta.getDivisaByDivisa().getSimbolo().contains("$")){ %>
            <a class="h5 text-muted"> <%=cuenta.getDivisaByDivisa().getNombre()%></a>
            <%}%>
            <a class="text-muted" style="float: right;font-size: 20%"> IBAN: <%=cuenta.getIdcuenta()%></a>
            </p>
            <a href="historial?id=<%=cuenta.getIdcuenta()%>" class="btn btn-outline-primary">Historial</a>
            <% if (cuenta.getActiva() == 1){%>
            <a style="margin-top:1%" href="trans?id=<%=cuenta.getIdcuenta()%>" class="btn btn-outline-primary">Realizar Transferencia</a>
            <a style="margin-top:1%" href="cambio?id=<%=cuenta.getIdcuenta()%>" class="btn btn-outline-primary">Realizar Cambio de Divisas</a>
            <a style="margin-top:1%" class="btn btn-outline-danger">Solicitar Desactivacion</a>
        </div>
    <%} else {%>
    <a style="margin-top:1%" class="btn btn-outline-success">Solicitar Activacion</a> <br/>

    <%}}}%>
</div>






<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>
