<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.example.skybank.entity.TipoOperacionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: luisruiznunez
  Date: 24/4/23
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");%>
<html>
<head>
    <title>Historial</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="container" style="align-items: center; text-align: center; margin-top: 5%;">
    <h1>Historial de Operaciones:</h1>
    <%if (operaciones != null) { %>
    <%for (OperacionEntity operacion : operaciones){ %>
    <div class="card" style="margin-bottom: 3%">
        <h2 style="margin-top: 1%;" class="card-title">Tipo: <%=operacion.getIdoperación()%></h2>

        <a>Cantidad: <%=operacion.getCantidad()%></a>
        <a datatype="date"><%=operacion.getFecha()%></a>
        <a> De la cuenta: <%=operacion.getCuentaByIdcuenta().getIdcuenta()%></a>
        <a> A la cuenta: <%=operacion.getCuentaByIdcuenta2().getIdcuenta()%></a>
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

