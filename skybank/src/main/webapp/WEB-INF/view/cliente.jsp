<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
%>

<html>
<head>
    <title>Datos del Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<div class="hidden">

</div>

<div class="container">
    <br/>
    <h1><%=cliente.getNombre()%>  <%=cliente.getApellidos()%></h1>
    <h2>Saldo: <%=cuenta.getSaldo()%> <%=cuenta.getDivisa()%></h2>
    <a class="btn btn-outline-primary">Modificar Datos</a>
    <a class="btn btn-outline-primary">Historial</a>
    <% if (cuenta.getActiva() == 1){%>
    <a class="btn btn-outline-primary">Realizar Transferencia</a>
    <a class="btn btn-outline-primary">Realizar Cambio de Divisas</a>
    <a class="btn btn-outline-danger">Solicitar Desactivacion</a>
    <% } else { %>
    <a class="btn btn-outline-primary">Solicitar Activacion</a>
    <%}%>
</div>






<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>
