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
    <title>Inicio Sesión de Empresa</title>
</head>
<body>

    <h1>Inicio Sesión de Empresa:</h1>

    <c:if test="${error != null}" >
        <p style="color:red;">
                ${error}
        </p>
    </c:if>

    <form:form method="post" action="/" cssStyle="display: flex; flex-direction: column;">
        <span>Nombre de la Empresa:</span>
        <form:input path="Nombre"></form:input>
        <br>

        <span>Nombre de la Empresa:</span>
        <form:input path="passwordEmpresa"></form:input>
        <br>

        <form:button> Iniciar Sesión</form:button>
    </form:form>

</body>
</html>
