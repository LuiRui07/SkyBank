<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registro de nueva Empresa</title>
</head>
<body>

    <h1>Registro de la empresa:</h1>

    <form:form method="post" action="/empresa/crearEmpresa" modelAttribute="empresa"  cssStyle="display: flex; flex-direction: column;">

        <div>
            <h2> Datos de la empresa</h2>
            <div>
                <span>CIF (*):</span>
                <form:input path="cif"></form:input>
            </div>

            <div>
                <span>Nombre de la Empresa (*):</span>
                <form:input path="nombre"></form:input>
            </div>

            <div>
                <span>Email Corporativo:</span>
                <form:input path="emailCorporativo"></form:input>
            </div>

        </div>


        <br>

        <div>
            <h2>Dirección</h2>
            <div style="display: flex;">
                <div>
                    <span>Calle(*):</span>
                    <form:input path="calle"></form:input>
                </div>

                <div>
                    <span>Número(*):</span>
                    <form:input type="number" path="numero"></form:input>

                </div>
            </div>
            <div>
                <span>Planta/Puerta/Ofcina (*):</span>
                <form:input type="number" path="planta"></form:input>
            </div>

            <div style="display: flex;">
                <div>
                    <span>Ciudad(*):</span>
                    <form:input path="ciudad"></form:input>
                </div>

                <div>
                    <span>Región:</span>
                    <form:input path="region"></form:input>

                </div>
            </div>

            <div style="display: flex;">
                <div>
                    <span>País(*):</span>
                    <form:input path="pais"></form:input>
                </div>

                <div>
                    <span>C.P.(*):</span>
                    <form:input path="cp"></form:input>

                </div>
            </div>

        <div style="margin-top: 10px; align-items: center">
            <span>Contraseña:</span>
            <form:input type="password" path="passwordEmpresa"></form:input>
            <br>
        <div>




        <form:button>Registrar</form:button>
    </form:form>

</body>
</html>
