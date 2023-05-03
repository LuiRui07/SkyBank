<%--
  @author: Luis Ruiz Nuñez
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.DivisaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
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
    <h3 class="display-2" style="margin-bottom: 3%">Cambio de Divisa</h3>
    <div>
    <form:form  modelAttribute="operacionCambio" method="get" action="/cliente/valorCambio" >
    Cantidad: <form:input cssStyle="text-align: center;" path="cantidad" size="15"></form:input> <a style="padding-right: 15%"><%=cuenta.getDivisaByDivisa().getNombre()%></a>
        Moneda <form:select path="divisaByDivisa" items="${divisas}" itemLabel="nombre"></form:select> <br/>
        <form:hidden path="cuentaByIdcuenta"></form:hidden>
    </div>
    <footer style="margin-left: -25%; margin-top: -1%;" class="blockquote-footer">Disponible <%=cuenta.getSaldo()%> <%=cuenta.getDivisaByDivisa().getNombre()%> </footer>
        <div class="container">
        <button class="btn btn-primary" style="margin-top: 6%;"> Continuar </button>
    </form:form>
        <a href="/cliente/" class="btn btn-danger" style="margin-top: 6%;" >Volver</a>
        </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

</body>
</html>