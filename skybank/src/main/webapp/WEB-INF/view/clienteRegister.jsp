<%--
  @author: Luis Ruiz Nuñez
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registar Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h1>Registro de Cliente:</h1>
    <form:form method="post" action="/cliente/crearCliente" modelAttribute="clienteNuevo"  cssStyle="display: flex; flex-direction: column;" cssClass="form-group">
    <div>
        <h2> Datos del Cliente</h2>
        <div style="display: flex; gap: 10px;">
            <span>DNI(*):</span>
            <form:input path="dni" cssClass="form-control " cssStyle="width: 20%" required="required"></form:input>
        </div>
        <div style="display: flex; gap: 10px; margin-top: 2%">
            <span>Nombre (*):</span>
            <form:input path="nombre" cssClass="form-control" cssStyle="width: 20%" required="required"></form:input>
        </div>

        <div style="display: flex; gap: 10px;margin-top: 2%">
            <span>Primer Apellido (*):</span>
            <form:input path="apellido1" cssClass="form-control" cssStyle="width: 20%" required="required"></form:input>
        </div>

        <div style="display: flex; gap: 10px;margin-top: 2%">
            <span>Segundo Apellido :</span>
            <form:input path="apellido2" cssClass="form-control" cssStyle="width: 20%"></form:input>
        </div>

        <div style="display: flex; gap: 10px;margin-top: 2%">
            <span>Email (*):</span>
            <form:input path="email" cssClass="form-control" cssStyle="width: 20%" required="required"></form:input>
        </div>
        <div style="display: flex; gap: 10px;margin-top: 2%">
            <span>Fecha de Nacimiento (*):</span>
            <form:input path="nacimiento" cssClass="form-control" cssStyle="width: 20%" type="date" required="required"></form:input>
        </div>

    </div>


    <br>

    <div>
        <h2>Dirección</h2>
        <div style="display: flex; gap: 10px;">
            <div>
                <span>Calle(*):</span>
                <form:input path="calle" cssClass="form-control" required="required"></form:input>
            </div>

            <div>
                <span>Número(*):</span>
                <form:input type="number" path="numero" cssClass="form-control" required="required"></form:input>

            </div>
        </div>
        <div>
            <span>Planta/Puerta/Oficina:</span>
            <form:input type="number" path="planta" cssClass="form-control" cssStyle="width: 41%"></form:input>
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
                <form:input path="cp" cssClass="form-control" required="required" ></form:input>

            </div>
        </div>

        <div style="margin-top: 10px; align-items: center">
            <span>Contraseña(*):</span>
                <form:input type="password" path="password" cssClass="form-control" cssStyle="width: 25%" required="required"  ></form:input>
            <br>
            <div class="container" style="">
                <form:button class="btn btn-outline-danger">Registrar</form:button>
                </form:form>

                <a  style=" margin-left: 3%" class="btn btn-outline-success" href="/cliente/logout">Ya tengo una cuenta</a>
            </div>



            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

</body>
</html>