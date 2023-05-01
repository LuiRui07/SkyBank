<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.example.skybank.entity.TipoOperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: luisruiznunez
  Date: 24/4/23
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");%>
<% CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");%>
<html>
<head>
    <title>Historial</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="container" style="align-items: center; text-align: center; margin-top: 5%;position: relative">
    <div class="container">
        <a href="/cliente/" class="btn btn-danger" style="position: absolute; left: 3%; top: 1.5%">Volver</a>
        <a class="display-3">Historial de Operaciones:</a>
    </div>

    <%if (operaciones != null) { %>
    <%for (OperacionEntity operacion : operaciones){ %>
    <div class="card" style="margin-bottom: 3%">
        <h2 style="margin-top: 1%;" class="card-title"><%=operacion.getTipoOperacionByTipopperacionid().getTipo()%></h2>

        <a>Cantidad: <%=operacion.getCantidad()%> <%=operacion.getDivisaByDivisa().getSimbolo()%></a>
        <% SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/YYYY");%>
        <a datatype="date"> Fecha: <%=dt1.format(operacion.getFecha())%></a>
        <% if (operacion.getTipoOperacionByTipopperacionid().getId() == 1){ %>
        <% if (operacion.getCuentaByIdcuenta().getIdcuenta() == cuenta.getIdcuenta()){ %>
            <a> Transferido a la cuenta: <%=operacion.getCuentaByIdcuenta2().getIdcuenta()%> </a>
        <%} else {%>
            <a> Recibido de la cuenta:  <%=operacion.getCuentaByIdcuenta().getIdcuenta()%> </a>
        <%}%> <%} else {%>
            <a> Cambio de Divisas a : <%= operacion.getDivisaByDivisa().getNombre()%></a>
        <% }%>
        <br/>

        <%if (operacion.getConcepto() != null) { %>
        Concepto: <a class="lead" style="color: cornflowerblue"><%=operacion.getConcepto()%></a>
        <%}%>
    </div>
    <%}} else { %>
     MEH
    <%}%>
</div>





<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>

