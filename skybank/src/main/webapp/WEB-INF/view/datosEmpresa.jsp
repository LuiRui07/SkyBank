<%--
  @author: José Luis López Ruiz
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Editar mi Empresa</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <h1>Datos de mi empresa:</h1>
    <form:form method="post" action="/empresa/editarEmpresa" modelAttribute="empresa"  cssStyle="display: flex; flex-direction: column;" cssClass="form-group">
    <form:hidden path="idempresa"></form:hidden>
    <form:hidden path="verificado"></form:hidden>
    <div>
        <div>
            <span>CIF (*):</span>
            <form:input path="cif" cssClass="form-control" required="required"></form:input>
        </div>

        <div>
            <span>Nombre de la Empresa (*):</span>
            <form:input path="nombre" cssClass="form-control" required="required"></form:input>
        </div>

        <div>
            <span>Email Corporativo:</span>
            <form:input path="emailcorporativo" cssClass="form-control" required="required"></form:input>
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
            <span>Planta/Puerta/Oficina (*):</span>
            <form:input type="number" path="planta" cssClass="form-control" required="required"></form:input>
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

        <div style="margin-top: 10px; align-items: center">
            <span>Contraseña:</span>
                <form:input type="password" path="passwordempresa" cssClass="form-control" required="required"></form:input>
            <br>
            <div>



                <form:button style="margin-bottom:10px;" class="btn btn-outline-danger">Editar</form:button>
                </form:form>
                <br>

            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>
