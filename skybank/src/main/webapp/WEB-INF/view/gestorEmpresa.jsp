<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.dto.Operacion" %>
<%@ page import="com.example.skybank.dto.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 05/05/2023
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");
  Empresa e = (Empresa) request.getAttribute("empresa");
%>
<html>
<head>
  <title><%=e.getNombre()%> - GESTIONAR</title>
</head>
<body>
<h1><%=e.getNombre()%></h1>
<h2>DATOS</h2>
CIF: <%=e.getCif()%><br>
EMAIL: <%=e.getEmailcorporativo()%><br>
DIRECCION: Calle <%=e.getCalle()%>, <%=e.getNumero()%> (<%=e.getCiudad()%>, <%=e.getPais()%>)<br>
<br><br>
<a href="historialEmpresa?postId=<%=e.getIdempresa()%>&tipo=1">Ver historial de operaciones</a>
<br>
<a href="/gestor/">VOLVER</a>
</body>
</html>
