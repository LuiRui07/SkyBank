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
<%@ page import="com.example.skybank.dto.Operacion" %>
<%@ page import="com.example.skybank.dto.Cuenta" %>
<%@ page import="com.example.skybank.ui.OrdenOperaciones" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");%>
<% Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");%>
<%  List<OrdenOperaciones> ordenes = new ArrayList<>();
    ordenes.add(new OrdenOperaciones(1,"Fecha - ASC"));
    ordenes.add(new OrdenOperaciones(2,"Fecha - DESC"));
    ordenes.add(new OrdenOperaciones(3,"Cantidad - ASC"));
    ordenes.add(new OrdenOperaciones(4,"Cantidad - DESC"));
    request.setAttribute("ordenes",ordenes);%>


<html>
<head>
    <title>Historial</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="container" style="align-items: center; text-align: center; margin-top: 5%;position: relative">
    <div class="container" style="margin-bottom: 4%">
        <a href="/cliente/" class="btn btn-danger" style="position: absolute; left: 3%; top: 1.5%">Volver</a>
        <a class="display-3">Historial de Operaciones:</a>
    </div>

    <form:form modelAttribute="filtro" method="post" action="/cliente/filtrar">
        <div style="margin-bottom: 2%">
            Ordenar por:
            <form:select path="ordenOperaciones.orden" multiple="false">
                <form:option value="" label="" />
                <form:options items="${ordenes}" itemLabel="orden" itemValue="orden"/>
            </form:select>
        </div>
        <form:select multiple="false" path="tipo" cssStyle="margin-right: 2%;">
            <form:option value="" label="Todos" />
            <form:options items="${tipos}" itemLabel="tipo" itemValue="tipo"/>
        </form:select>
        <form:hidden path="idCuenta"></form:hidden>
        Desde:<form:input type="date" path="desde"  size="15"></form:input>
        Hasta: <form:input type="date" path="hasta" cssStyle="margin-right: 3%" size="15"></form:input>


        <form:input path="min" placeholder="Minima cantidad" size="15"></form:input>
        <form:input path="max" placeholder="Maxima cantidad" size="15"></form:input>
        <button style="margin-left: 2%" class="btn btn-info">Filtrar</button>
    </form:form>

    <%if (operaciones != null) { %>
    <%for (Operacion operacion : operaciones){ %>
    <div class="card" style="margin-bottom: 3%">
        <h2 style="margin-top: 1%;" class="card-title"><%=operacion.getTipoOperacion().getTipo()%></h2>
        <% SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/YYYY");%>
        <a datatype="date"> Fecha: <%=dt1.format(operacion.getFecha())%></a>
        <% if (operacion.getTipoOperacion().getId() == 1){ %>
        <% if (operacion.getCuentaOrigen().getIdcuenta() == cuenta.getIdcuenta()){ %>
            <div>
                Cantidad: <a style="color: crimson"> -<%=operacion.getCantidad()%> <%=operacion.getDivisa().getSimbolo()%></a>
            </div>
            <a> Transferido a la cuenta: <%=operacion.getCuentaDestino().getIdcuenta()%> </a>
        <%} else {%>
            <div>
            Cantidad: <a style="color: forestgreen"> +<%=operacion.getCantidad()%> <%=operacion.getDivisa().getSimbolo()%></a>
            </div>
            <a> Recibido de la cuenta:  <%=operacion.getCuentaOrigen().getIdcuenta()%> </a>
        <%}%> <%} else {%>
            <%if (operacion.getDivisa() == cuenta.getDivisa()){ %>
            <div>
            Cantidad: <a style="color: forestgreen"> +<%=operacion.getCantidad()%> <%=cuenta.getDivisa().getSimbolo()%></a>
            </div>
            <%} else { %>
            Cantidad: <a style="color: crimson"> -<%=operacion.getCantidad()%> <%=cuenta.getDivisa().getSimbolo()%></a>
            <%}%>
            <a> Cambio de Divisas a : <%= operacion.getDivisa().getNombre()%></a>
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

