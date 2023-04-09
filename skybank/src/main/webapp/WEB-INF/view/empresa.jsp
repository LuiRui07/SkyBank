<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 20/2/23
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");
%>

<html>
<head>
    <title>Datos de la Empresa</title>
</head>
<body>

    <h1> Datos de la empresa <%=empresa.getNombre()%></h1>


</body>
</html>
