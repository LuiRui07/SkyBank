<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    Set<CuentaEntity> cuentas = (Set<CuentaEntity>) request.getAttribute("cuentas");
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
    <h1><%=cliente.getNombre()%>  <%=cliente.getApellido1()%> <%=cliente.getApellido2()%>
        <a style="margin-left: 60.8%" href="editar?id=${cliente.idCliente}" class="btn btn-outline-primary">Modificar Datos</a>
    </h1>

    <% for (CuentaEntity cuenta : cuentas) { %>
        <div class="card" style="margin-bottom: 3%">
            <h2 style="margin-top: 1%;" class="card-title">Saldo: <%=cuenta.getSaldo()%> <%=cuenta.getDivisa()%></h2>

            <a href="historial?id=<%=cuenta.getIdCuenta()%>" class="btn btn-outline-primary">Historial</a>
            <% if (cuenta.getActiva() == 1){%>
            <a style="margin-top:1%" class="btn btn-outline-primary">Realizar Transferencia</a>
            <a style="margin-top:1%" class="btn btn-outline-primary">Realizar Cambio de Divisas</a>
            <a style="margin-top:1%" class="btn btn-outline-danger">Solicitar Desactivacion</a>
        </div>
    <%} else {%>
    <a style="margin-top:1%" class="btn btn-outline-success">Solicitar Activacion</a> <br/>

    <%}}%>
    <div>
        <a href="logout" style="margin-top: 0%;" class="btn btn-outline-info"> Cerrar Sesión </a>
    </div>
</div>






<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>
