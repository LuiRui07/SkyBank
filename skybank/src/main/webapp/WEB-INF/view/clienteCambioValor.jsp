<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="com.example.skybank.entity.DivisaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: luisruiznunez
  Date: 1/5/23
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% OperacionEntity operacion = (OperacionEntity) request.getAttribute("operacion");%>


<html>
<head>
    <title>Cambio a <%=operacion.getDivisaByDivisa().getNombre()%></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


</head>
<body>
<% Double cambio = operacion.getCantidad() * operacion.getDivisaByDivisa().getValor();%>
<div class="container" style="align-items: center; text-align: center; margin-top: 5%;>
    <h1 style="text-align: center> Valor estimado: </h1> <br/>
    <a style="text-align: center"><%=String.format("%.2f",cambio)%> <%=operacion.getDivisaByDivisa().getSimbolo()%></a>
    <form:form modelAttribute="operacionCambio" method="post" action="/cliente/doDivisa">
        <form:hidden path="cantidad"></form:hidden>
        <form:hidden path="cuentaByIdcuenta"></form:hidden>
        <form:hidden path="divisaByDivisa"></form:hidden>
        <button style="margin-top: 5%;" class="btn btn-primary"> Confirmar Cambio </button>
    </form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

</body>
</html>
