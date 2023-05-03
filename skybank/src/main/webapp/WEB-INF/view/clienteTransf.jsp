<%--
  @author: Luis Ruiz Nuñez
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<CuentaEntity> cuentas = (List<CuentaEntity>) request.getAttribute("cuentas");%>
<% CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuentaOrigen");%>
<html>
<head>
    <title>Realizar Transferencia</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

    <div class="container" style="align-items: center; text-align: center; margin-top: 5%;">
        <h3 class="display-3">Realizar Transferencia</h3>
    </div>
        <div>
            <a class="display-4" style="margin-left: 5%"> Elegir cantidad </a>
            <a class="display-4" style="margin-left: 35%">Elegir Destinatario </a>
        </div>

        <form:form modelAttribute="operacion" method="post" action="/cliente/doTransf">
        <form:hidden path="cuentaByIdcuenta"></form:hidden>
        <form:hidden path="divisaByDivisa"></form:hidden>
        <div>
            <form:input cssStyle="text-align: center; margin-left: 10%; margin-top: 2%" path="cantidad"></form:input> <a style="padding-right: 15%"><%=cuenta.getDivisaByDivisa().getNombre()%></a>
            <form:select cssStyle="float: right; margin-right: 20%; margin-top: 2%;" path="cuentaByIdcuenta2" items="${cuentas}" ></form:select>
        </div>
        <footer style="margin-left: 10%" class="blockquote-footer">Disponible <%=cuenta.getSaldo()%> <%=cuenta.getDivisaByDivisa().getNombre()%> </footer>


        <form:input placeholder="Concepto" cssStyle="margin-top: 3%; margin-left: 6%; text-align: center" path="concepto" size="30"></form:input>

        <div style="margin-left: 44%; margin-top: 8%">
            <button class="btn btn-primary"> Confirmar </button>
            <a href="/cliente/" class="btn btn-danger" >Volver</a>
        </div>
    </form:form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

</body>
</html>
