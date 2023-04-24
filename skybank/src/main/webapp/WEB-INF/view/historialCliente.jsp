<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: luisruiznunez
  Date: 24/4/23
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Set< OperacionEntity> operaciones = (Set<OperacionEntity>) request.getAttribute("operaciones");%>
<html>
<head>
    <title>Historial</title>
</head>
<body>
<h1>Historial de Operaciones:</h1>

</body>
</html>

