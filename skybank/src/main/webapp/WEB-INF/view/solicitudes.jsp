<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: Rafael Ceballos
  Date: 03/05/2023
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> solicitadas = (List<ClienteEntity>) request.getAttribute("solicitadas");
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
        </thead>
        <% for (ClienteEntity c:solicitadas){ %>
        <tr>
            <td>Cliente</td>
            <td><%=c.getNombre()%> <%=c.getApellido1()%> <%=c.getApellido2()%></td>
            <td><%=c.getDni()%></td>

            <td><a href="aceptar?postId=<%=c.getIdcliente()%>">Aceptar</a></td>
        </tr>
        <% } %>
    </table>

</body>
</html>