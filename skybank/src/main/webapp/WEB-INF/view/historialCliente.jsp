<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 06/05/2023
  Time: 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");
%>
<html>
<head>
    <title>HISTORIAL</title>
</head>
<body>
<% if(cliente != null){%>
<h1><%=cliente.getNombre()%></h1>
<% } else{%>
<h1><%=empresa.getNombre()%></h1>
<% }%>
<h2>HISTORIAL OPERACIONES</h2>
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
            <% for(OperacionEntity o : operaciones){%>
            <tr>
                <td><%=o.getTipoOperacionByTipopperacionid().getTipo()%></td>
                <td><%=o.getCuentaByIdcuenta().getIdcuenta()%></td>
                <td><%=o.getCuentaByIdcuenta2().getIdcuenta()%></td>
                <td><%=o.getCantidad()%><%=o.getDivisaByDivisa().getSimbolo()%></td>
                <td><%=o.getFecha()%></td>
                <% if (o.getConcepto() != null){%>
                <td><%=o.getConcepto()%></td>
            </tr>
            <% } }%>
        </tbody>
    </table>
<br>
<% if(cliente != null){%>
<a href="gestionarCliente?postId=<%=cliente.getIdcliente()%>">VOLVER</a>
<% }else {%>
<a href="gestionarEmpresa?postId=<%=empresa.getIdempresa()%>">VOLVER</a>
<% } %>
</body>
</html>
