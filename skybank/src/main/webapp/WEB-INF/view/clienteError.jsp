<%@ page import="com.example.skybank.dto.Operacion" %>
<%@ page import="com.example.skybank.dto.Cuenta" %><%--
  @author: Luis Ruiz Nuñez
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% int Tipo = (Integer) request.getAttribute("tipo");%>
<% Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");%>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container" style="align-items: center; text-align: center; margin-top: 5%;">
    <h1 class="display-1"> Error: No hay Saldo Suficiente o es Incorrecto </h1>
    <%if (Tipo == 1){ %>
    <a href="/cliente/trans?id=<%=cuenta.getIdcuenta()%>" style="color: white" class="btn btn-danger"> Aceptar</a>
    <%} else { %>
        <a href="/cliente/cambio?id=<%=cuenta.getIdcuenta()%>" style="color: white" class="btn btn-danger"> Aceptar</a>
    <%}%>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

</body>
</html>
