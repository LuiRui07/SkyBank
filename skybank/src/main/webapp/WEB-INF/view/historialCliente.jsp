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
    Cliente cliente = (Cliente) request.getAttribute("cliente");
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
</head>
<body>
<h2>HISTORIAL OPERACIONES</h2>
<div>
    <form:form modelAttribute="filtro" method="post" action="/gestor/filtrar" >
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
</div>


    <table border="1">
        <thead>
            <td>TIPO OPERACION</td>
            <td>CUENTA ORIGEN</td>
            <td>CUENTA DESTINO</td>
            <td>CANTIDAD</td>
            <td>FECHA</td>
            <td>CONCEPTO</td>
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
<a href="gestionarCliente?postId=<%=cliente.getIdcliente()%>">VOLVER</a>

</body>
</html>
