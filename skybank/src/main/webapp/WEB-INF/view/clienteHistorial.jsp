<%--
  @author: Luis Ruiz Nuñez
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.example.skybank.entity.TipoOperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.skybank.ui.FiltroOperaciones" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");%>
<% CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");%>
<% FiltroOperaciones filtro = (FiltroOperaciones) request.getAttribute("filtro");%>
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
    <form:form modelAttribute="filtro" method="post" action="/cliente/filtrar">
        <form:hidden path="idCuenta"></form:hidden>
        Desde:<form:input type="date" path="desde"  size="15"></form:input>
        Hasta: <form:input type="date" path="hasta" cssStyle="margin-right: 3%" size="15"></form:input>
        <form:select multiple="false" path="tipo" cssStyle="margin-right: 2%;">
            <form:option value="" label="Todos" />
            <form:options items="${tipos}" itemLabel="tipo" itemValue="tipo"/>
        </form:select>

        <form:input path="min" placeholder="Minima cantidad"></form:input>
        <form:input path="max" placeholder="Maxima cantidad"></form:input>
        <button style="margin-right: 1%" class="btn btn-info">Filtrar</button>
    </form:form>

    <%if (operaciones != null) { %>
    <%for (OperacionEntity operacion : operaciones){ %>
    <div class="card" style="margin-bottom: 3%">
        <h2 style="margin-top: 1%;" class="card-title"><%=operacion.getTipoOperacionByTipopperacionid().getTipo()%></h2>
        <% SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/YYYY");%>
        <a datatype="date"> Fecha: <%=dt1.format(operacion.getFecha())%></a>
        <% if (operacion.getTipoOperacionByTipopperacionid().getId() == 1){ %>
        <% if (operacion.getCuentaByIdcuenta().getIdcuenta() == cuenta.getIdcuenta()){ %>
            <div>
                Cantidad: <a style="color: red"> -<%=operacion.getCantidad()%> <%=operacion.getDivisaByDivisa().getSimbolo()%></a>
            </div>
            <a> Transferido a la cuenta: <%=operacion.getCuentaByIdcuenta2().getIdcuenta()%> </a>
        <%} else {%>
            <div>
            Cantidad: <a style="color: green"> +<%=operacion.getCantidad()%> <%=operacion.getDivisaByDivisa().getSimbolo()%></a>
            </div>
            <a> Recibido de la cuenta:  <%=operacion.getCuentaByIdcuenta().getIdcuenta()%> </a>
        <%}%> <%} else {%>
            <a> Cambio de Divisas a : <%= operacion.getDivisaByDivisa().getNombre()%></a>
        <% }%>
        <br/>

        <%if (operacion.getConcepto() != null) { %>
        Concepto: <a class="lead" style="color: cornflowerblue"><%=operacion.getConcepto()%></a>
        <%}%>
    </div>
    <%}}%>
</div>





<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>

