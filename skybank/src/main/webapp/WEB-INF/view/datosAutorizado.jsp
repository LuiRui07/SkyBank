<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Pepe
  Date: 26/04/2023
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container mt-3">
  <form:form modelAttribute="autorizado" cssClass="rounded bg-light p-4" action="/empresa/autorizados/edit?eId=${autorizado.empresaByIdEmpresa.idEmpresa}" method="post">

  <h1>Tipo de cuenta: <strong style="color: darkred;">Autorizado</strong></h1>
  <h3>Autorizado en la empresa: <span style="color: darkred">${autorizado.empresaByIdEmpresa.nombre}</span></h3>

  <hr>
  <h2>Mis Datos:</h2>
  <form:hidden path="id"></form:hidden>
  <form:hidden path="empresaByIdEmpresa"></form:hidden>
  <div>
    <span>NIF (*):</span>
    <form:input path="nif" cssClass="form-control" required="required"></form:input>
  </div>

  <div>
    <span>Nombre (*):</span>
    <form:input path="nombre" cssClass="form-control" required="required"></form:input>
  </div>

  <div>
    <span>Primer Apellido (*):</span>
    <form:input path="apellido1" cssClass="form-control" required="required"></form:input>
  </div>

  <div>
    <span>Segundo Apellido:</span>
    <form:input path="apellido2" cssClass="form-control"></form:input>
  </div>

  <div>
    <span>Email:</span>
    <form:input path="email" cssClass="form-control" required="required"></form:input>
  </div>

  <div>
    <span>Fecha de nacimiento:</span>
    <form:input path="fechanacimiento" type="date" cssClass="form-control" required="required"></form:input>
  </div>


  <br>

  <div style="margin-top: 10px; align-items: center; width: 20%;">
    <span>Contraseña:</span>
      <form:input type="password" path="password" cssClass="form-control" required="required"></form:input>
    <br>
    <div>

      <div style="width: max-content;">
        <h2>Dirección</h2>
        <div style="display: flex; gap: 10px; width: 100%;">
          <div>
            <span>Calle(*):</span>
            <form:input path="calle" cssClass="form-control" required="required"></form:input>
          </div>

          <div>
            <span>Número(*):</span>
            <form:input type="number" path="numero" cssClass="form-control" required="required"></form:input>
          </div>

          <div>
            <span>Planta/Puerta/Oficina (*):</span>
            <form:input type="number" path="planta" cssClass="form-control" required="required"></form:input>
          </div>
        </div>


        <div style="display: flex; gap: 10px;">
          <div>
            <span>Ciudad(*):</span>
            <form:input path="ciudad" cssClass="form-control" required="required"></form:input>
          </div>

          <div>
            <span>Región:</span>
            <form:input path="region" cssClass="form-control"></form:input>

          </div>
        </div>

        <div style="display: flex; gap: 10px;">
          <div>
            <span>País(*):</span>
            <form:input path="pais" cssClass="form-control" required="required"></form:input>
          </div>

          <div>
            <span>C.P.(*):</span>
            <form:input path="cp" cssClass="form-control" required="required"></form:input>

          </div>
        </div>
      </div>

      <br>

      <form:button style="margin-bottom:10px;" class="btn btn-outline-danger">Editar</form:button>

      </form:form>

      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
