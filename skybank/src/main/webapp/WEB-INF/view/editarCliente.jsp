<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: luisruiznunez
  Date: 24/4/23
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");%>
<html>
<head>
    <title>Modificar Datos</title>
</head>
<body>
<h1> Modificar Datos </h1>
  <div class="container"></div>
      <form:form modelAttribute="cliente" action="/cliente/editar" method="post">
        <form:input path="nombre" maxlength="30" size="30"></form:input> <br/> <br/>
        <form:input path="apellidos" maxlength="50" size="50"></form:input>  <br/> <br/>
        <form:input path="dni"></form:input>  <br/> <br/>
        <form:input path="direccion"></form:input>  <br/> <br/>
        <form:input path="edad"></form:input> <br/> <br/>
        <form:input path="email"></form:input>  <br/> <br/>
        <form:button>Guardar</form:button>
      </form:form>
</body>
</html>
