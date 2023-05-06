<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="com.example.skybank.dto.Cliente" %>
<%@ page import="com.example.skybank.dto.Operacion" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 05/05/2023
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");
    Cliente cliente = (Cliente) request.getAttribute("cliente");

%>
<html>
<head>
    <title><%=cliente.getNombre()%> <%=cliente.getApellido1()%> - GESTIONAR</title>
</head>
<body>
<h1><%=cliente.getNombre()%> <%=cliente.getApellido1()%> <%=cliente.getApellido2()%></h1>
<h2>DATOS</h2>
    DNI: <%=cliente.getDni()%><br>
    EMAIL: <%=cliente.getEmail()%><br>
    DIRECCION: Calle <%=cliente.getCalle()%>, <%=cliente.getNumero()%> (<%=cliente.getCiudad()%>, <%=cliente.getPais()%>)<br>
    FECHA DE NACIMIENTO: <%=cliente.getNacimiento()%>
<br><br>
<a href="historial?postId=<%=cliente.getIdcliente()%>&tipo=0">Ver historial de operaiones</a>
<br>
<a href="/gestor/">VOLVER</a>
</body>
</html>
