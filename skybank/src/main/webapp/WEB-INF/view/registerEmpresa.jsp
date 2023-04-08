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

<html>
<head>
    <title>Registro de nueva Empresa</title>
</head>
<body>

    <h1>Registro de la empresa:</h1>

    <form:form modelAttribute="nuevaEmpresa" method="post" action="/" cssStyle="display: flex; flex-direction: column;">
        <span>Nombre de la Empresa:</span>
        <form:input path="Nombre"></form:input>
        <br>
        <span>Email Corporativo:</span>
        <form:input path="EmailCorporativo"></form:input>
        <br>

        <span>Contraseña Corporativa:</span>
        <form:input path="passwordEmpresa"></form:input>
        <br>

        <span>Repite la Contraseña Corporativa:</span>
        <form:input path="passwordEmpresa"></form:input>
        <br>
        <form:button> Registrar</form:button>
    </form:form>

</body>
</html>
