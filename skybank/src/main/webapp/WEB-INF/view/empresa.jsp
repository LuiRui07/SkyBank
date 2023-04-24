<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");
    List<CuentaEntity> cuentas = empresa.getCuentasByIdEmpresa();
%>

<html>
<head>
    <title>Datos de la Empresa</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

    <jsp:include page="header.jsp" />

    <div class="hidden">

    </div>

    <div class="container">
        <%
            System.out.println(empresa.getSociosByIdEmpresa());

            if (empresa.getSociosByIdEmpresa().size() == 0) {

        %>

        <div class="alert alert-danger container mt-4" role="alert">
            La empresa no tiene ningun socio.
            <br>
            <a href="/empresa/socios/#addSocio" style="color: darkred; font-weight: bold;">[+] Añadir nuevo socio</a>
        </div>

        <%
            }
        %>
        <h1> Datos de tus cuentas: </h1>



        <p class="lead"> Tienes un total de <%=cuentas.size()%> cuentas.</p>

        <%
            for(CuentaEntity cuenta : cuentas){

        %>
        <p>

            <a class="btn btn-success" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                ID Cuenta:  <%=cuenta.getIdCuenta()%>
            </a>

        </p>
        <div class="collapse" id="collapseExample">
            <div class="card card-body d-flex flex-row align-middle" >
                <div>
                    <p class="display-5">Saldo disponible:</p>
                    <p class="display-1 p-3 rounded bg-light"><%=cuenta.getSaldo()%></p>
                </div>
                <div class="ml-5 mt-5">
                    <p class="display-4">Divisa: <%=cuenta.getDivisa()%></p>
                    <p>Cuenta Sospechosa: <%=cuenta.getSospechosa() == 1 ? "Si" : "No" %></p>
                </div>
            </div>
        </div>

        <%
            }
        %>


    </div>






    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>
