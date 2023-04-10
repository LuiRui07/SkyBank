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

    <form method="post" action="/empresa/login" style="display: flex; flex-direction: column;">
        <label for="nombre">Nombre de la Empresa:</label>
        <input id="nombre" name="nombre" required/>
        <br>

        <label for="password">Contraseña:</label>
        <input id="password" type="password" name="password" required/>
        <br>

        <input type="submit" value="Iniciar Sesión"/>
    </form>

    <a href="/empresa/register">¡Registrate como empresa!</a>

</body>
</html>
