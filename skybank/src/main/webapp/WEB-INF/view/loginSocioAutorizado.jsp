<%--
  @author: José Luis López Ruiz
--%>

<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Inicia Sesión como Socio o Autorizado</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

    <div class="container mt-3">


        <h1>Inicia Sesión como Socio o Autorizado:</h1>


        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {

        %>

            <div class="alert alert-danger container" role="alert">
                    ${error}
            </div>

        <%
            }
        %>

        <form method="post" action="/empresa/socios/login" style="display: flex; flex-direction: column;" class="form-group">

            <label for="nie">NIF:</label>
            <input id="nie" type="text" name="nif" class="form-control" required/>
            <br>

            <label for="password">Contraseña:</label>
            <input id="password" type="password" name="password" class="form-control" required/>
            <br>

            <input type="submit" class="btn btn-outline-danger" value="Iniciar Sesión"/>
        </form>

        <a href="/empresa/login">¡Inicia Sesión como Empresa!</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
