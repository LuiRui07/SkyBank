<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.ui.OrdenOperaciones" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.skybank.ui.FiltroOperaciones" %>
<%@ page import="com.example.skybank.dto.Cliente" %>
<%@ page import="com.example.skybank.dto.Empresa" %>
<%@ page import="com.example.skybank.dto.Operacion" %>
<%@ page import="com.example.skybank.dto.Cuenta" %><%--
  Created by IntelliJ IDEA.
  User: Rafael Ceballos
  Date: 06/05/2023
  Time: 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");
  Empresa empresa = (Empresa) request.getAttribute("empresa");
  Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
  List<OrdenOperaciones> ordenes = new ArrayList<>();
  ordenes.add(new OrdenOperaciones(1,"Fecha - ASC"));
  ordenes.add(new OrdenOperaciones(2,"Fecha - DESC"));
  ordenes.add(new OrdenOperaciones(3,"Cantidad - ASC"));
  ordenes.add(new OrdenOperaciones(4,"Cantidad - DESC"));
  request.setAttribute("ordenes",ordenes);
%>
<html>
<head>
  <title>HISTORIAL</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container mt-4">
<h1 class="display-3 mt-5 mb-4">HISTORIAL OPERACIONES</h1>
<div>
  <form:form modelAttribute="filtro" method="post" action="/gestor/filtrar2" >
    <div style="margin-bottom: 2%">
      Ordenar por:
      <form:select path="ordenOperaciones.orden" multiple="false">
        <form:option value="" label="" />
        <form:options items="${ordenes}" itemLabel="orden" itemValue="orden"/>
      </form:select>
    </div>
    Tipo de operacion:
    <form:select multiple="false" path="tipo" cssStyle="margin-right: 2%;">
      <form:option value="" label="Todos" />
      <form:options items="${tipos}" itemLabel="tipo" itemValue="tipo"/>
    </form:select>
    <form:hidden path="idCuenta"></form:hidden>
    Desde:<form:input type="date" path="desde"  size="15"></form:input>
    Hasta: <form:input type="date" path="hasta" cssStyle="margin-right: 3%" size="15"></form:input>


    <form:input path="min" placeholder="Minima cantidad" size="15"></form:input>
    <form:input path="max" placeholder="Maxima cantidad" size="15"></form:input>
    <button style="margin-left: 2%" class="btn btn-warning">Filtrar</button>
  </form:form>
</div>


<table class="table">
  <thead>
  <th scope="col">TIPO OPERACION</th>
  <th scope="col">CUENTA ORIGEN</td>
  <th scope="col">CUENTA DESTINO</td>
  <th scope="col">CANTIDAD</td>
  <th scope="col">FECHA</td>
  <th scope="col">CONCEPTO</td>
  </thead>
  <tbody>
  <% for(Operacion o : operaciones){%>
  <tr>
    <td><%=o.getTipoOperacion().getTipo()%></td>
    <td><%=o.getCuentaOrigen().getIdcuenta()%></td>
    <td><%=o.getCuentaDestino().getIdcuenta()%></td>
    <td><%=o.getCantidad()%><%=o.getDivisa().getSimbolo()%></td>
    <td><%=o.getFecha()%></td>
    <% if (o.getConcepto() != null){%>
    <td><%=o.getConcepto()%></td>
  </tr>
  <% } }%>
  </tbody>
</table>
<br>
<a href="gestionarEmpresa?postId=<%=empresa.getIdempresa()%>" class="btn btn-danger">VOLVER</a>
</div>
</body>
</html>
