<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: Rafael Ceballos
  Date: 03/05/2023
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<CuentaEntity> solicitadas = (List<CuentaEntity>) request.getAttribute("solicitadas");
%>
<html>
<head>
    <title>SOLICITUDES</title>
</head>
<body>
<h1>Lista de solicitudes</h1>
    <table border="1">
        <thead>
        <td>CLIENTE/EMPRESA</td>
        <td>NOMBRE</td>
        <td>DNI/CIF</td>
        <td>ACTIVAR</td>
        <td>DESACTIVAR</td>
        </thead>
        <% for (CuentaEntity c:solicitadas){ %>
        <tr>
            <% if(c.getClienteByIdcliente() == null && c.getEmpresaByIdempresa() != null){
            %>
            <td>Empresa</td>
            <td><%=c.getEmpresaByIdempresa().getNombre()%></td>
            <td><%=c.getEmpresaByIdempresa().getCif()%></td>

            <% }else if(c.getClienteByIdcliente() != null && c.getEmpresaByIdempresa() == null){ %>
            <td>Cliente</td>
            <td><%=c.getClienteByIdcliente().getNombre()%></td>
            <td><%=c.getClienteByIdcliente().getDni()%></td>
            <% }%>
            <td><a href="aceptar?postId=<%=c.getIdcuenta()%>">Aceptar</a></td>
            <td><a href="rechazar?postId=<%=c.getIdcuenta()%>">Rechazar</a></td>
        </tr>
        <% } %>
    </table>

</body>
</html>