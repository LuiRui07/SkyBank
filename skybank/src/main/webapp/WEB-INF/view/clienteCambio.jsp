<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.DivisaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: luisruiznunez
  Date: 27/4/23
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuentaCambio");%>
<% List<DivisaEntity> divisas = (List<DivisaEntity>) request.getAttribute("divisas");%>
<% OperacionEntity op = (OperacionEntity) request.getAttribute("operacionCambio");%>
        <html>
<head>
    <title>Cambio de divisas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container" style="align-items: center; text-align: center; margin-top: 5%;">
    <h3 class="display-1">Cambio</h3>

    <form:form  modelAttribute="operacionCambio" method="get" action="/cliente/valorCambio" >
        Cantidad: <form:input cssStyle="text-align: center" path="cantidad"></form:input>
        Moneda <form:select path="divisaByDivisa" items="${divisas}" itemLabel="nombre"></form:select> <br/>
        <form:hidden path="cuentaByIdcuenta"></form:hidden>
       <button class="btn btn-outline-primary" style="margin-top: 6%;"> Continuar </button>
    </form:form>

</div>

</body>
</html>