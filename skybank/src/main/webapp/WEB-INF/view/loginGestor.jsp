<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    @author Rafael Ceballos
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>INICIO-GESTOR</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="container">


  <h1 class="mt-5">Inicio Sesión como Gestor:</h1>
    <% String error = (String) request.getAttribute("error");
        if (error != null) { %>
  <div class="alert alert-danger mt-3" role="alert">
    ${error}
  </div>
    <% } %>

  <form method="post" action="/gestor/login" class="mt-3">
    <div class="mb-3">
      <label for="DNI" class="form-label">DNI:</label>
      <input type="text" class="form-control" id="DNI" name="DNI" required>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">Contraseña:</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <div>
      <button type="submit" class="btn btn-outline-danger">Iniciar Sesión</button>
      <a class="btn btn-outline-secondary" href="/empresa/logout" style="margin-left: 55%"> Iniciar como Empresa</a>
      <a class="btn btn-outline-secondary ml-3" href="/cliente/login"> Iniciar como Cliente</a>
    </div>

  </form>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
