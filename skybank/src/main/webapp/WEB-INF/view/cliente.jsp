<%--
  @author: Luis Ruiz Nuñez
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.dto.Cliente" %>
<%@ page import="com.example.skybank.dto.Cuenta" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
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
    <script>
        function CerrarSesion() {
        var result = confirm("¿Seguro que quieres cerrar sesión?");
            if (result == true) {
            window.location = "/cliente/logout"
            }
        }
    </script>

    <p class="h1"><%=cliente.getNombre()%>  <%=cliente.getApellido1()%> <%=cliente.getApellido2()%>
        <div>
            <a href="editar?id=${cliente.idcliente}" class="btn btn-outline-primary">Modificar Datos</a>
            <a onclick="CerrarSesion()" style="float:right" class="btn btn-outline-info"> Cerrar Sesión </a>
            <a href="/cliente/chatsCliente"  style="float:right; margin-right: 1%" class="btn btn-outline-success">Contactar con Asistente</a>

            <%if (cliente.getBloqueado() == 1 && cliente.getSolicitudactivacion() != 1){%>
                <a href="solicitar?id=<%=cliente.getIdcliente()%>"  class="btn btn-outline-warning">Solicitar Activacion</a> <br/>
        </div>
    </p>

    <a class="display-1" style="align-items: center; text-align: center;" >Cuenta de Cliente Bloqueada</a>

    <%} else if (cliente.getSolicitudactivacion() == 1) {%>
        </div>
    </p>
    <a class="display-1" style="align-items: center; text-align: center;" >Cuenta de Cliente Solicitada para Desbloqueo</a>
    <%} else {%>
        </div>
    </p>
    <% for (Cuenta cuenta : cuentas) { %>
        <%if (!((cuenta.getSaldo() - 0.01) < 0 )){%>
        <div class="card" style="margin-bottom: 3%">
            <p class="display-3 p-3 rounded bg-light"><%=cuenta.getSaldo()%>     <%=cuenta.getDivisa().getSimbolo()%>
            <%if (cuenta.getDivisa().getSimbolo().contains("$")){ %>
            <a class="h5 text-muted"> <%=cuenta.getDivisa().getNombre()%></a>
            <%}%>
            <a class="text-muted" style="float: right;font-size: 20%"> IBAN: <%=cuenta.getIdcuenta()%></a>
            </p>
            <a href="historial?id=<%=cuenta.getIdcuenta()%>" class="btn btn-outline-info">Historial</a>
            <% if (cuenta.getActiva() == 1){%>
            <a style="margin-top:1%" href="trans?id=<%=cuenta.getIdcuenta()%>" class="btn btn-outline-primary">Realizar Transferencia</a>
            <a style="margin-top:1%" href="cambio?id=<%=cuenta.getIdcuenta()%>" class="btn btn-outline-primary">Realizar Cambio de Divisas</a>
        </div>
    <%} else { %>
        <h1 style="margin-top:1%; color: white"class="badge bg-danger" >Cuenta Bloqueada</h1>

    <%}}}}%>
</div>






<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>
