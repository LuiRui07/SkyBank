<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Email" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%--
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
  <div class="container" style="align-items: center; text-align: center">
    <h1  class="display-4"style="margin-bottom: 10%; margin-top: 5%; "> Modificar Datos </h1>
      <form:form modelAttribute="cliente" action="/cliente/editar" method="post">
      <form:hidden path="idcliente"></form:hidden>
       Nombre: <form:input cssStyle="margin-right: 10%" path="nombre" maxlength="30" size="15"></form:input>
       Primer Apellido: <form:input cssStyle="margin-right: 10%" path="apellido1" maxlength="50" size="15"></form:input>
       Segundo Apellido: <form:input path="apellido2" maxlength="50" size="15"></form:input> <br/> <br/>
       DNI: <form:input  path="dni" cssStyle="margin-right: 5%" size="15"></form:input>
       Fecha de Nacimiento: <form:input path="nacimiento" cssStyle="margin-right: 10%" type="date"  size="15"></form:input>
       Email: <form:input path="email"  size="15"></form:input>  <br/> <br/>
       <br/>
          <h3 class=""> Direccion: </h3> <br/>
       Calle:   <form:input path="calle" size="15"></form:input>
       Numero: <form:input path="numero" size="15"></form:input>
       Planta: <form:input path="planta" size="15"></form:input>
       CP:   <form:input path="cp" size="15"></form:input>  <br/> <br/>
       Ciudad: <form:input path="ciudad" size="15"></form:input>
       Region:<form:input path="region" size="15"></form:input>
       Pais:<form:input path="pais" size="15"></form:input> <br/>

          <button class="btn btn-success" style="margin-top: 2%;"   > Guardar
          </button>
      </form:form>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
