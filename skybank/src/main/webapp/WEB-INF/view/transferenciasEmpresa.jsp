<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: Pepe
  Date: 11/04/2023
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");
    List<List<OperacionEntity>> transferencias = (List<List<OperacionEntity>>) request.getAttribute("transferencias");
%>

<html>
<head>
    <title>Transferencias</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container mt-3">

        <div class="card">
            <div class="card-title text-center">
                <h1 class="display-5 mt-3">Realizar una nueva transferencia</h1>
            </div>
            <div class="card-body">
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

                <%
                    String success = (String) request.getAttribute("success");
                    if (success != null) {

                %>
                <div class="alert alert-success container" role="alert">
                    ${success}
                </div>
                <%
                    }
                %>


                <form method="post" action="/empresa/transferencias/nueva">
                    <div style="display: flex; gap: 10px; align-items: center; justify-content: center;">
                        <label for="cantidad">Cantidad a transferir: </label>
                        <input name="cantidad" id="cantidad" type="number" min="1" step="0.01" class="form-control"  style="width: 100px; height: fit-content;" required/>
                    </div>

                    <div style="display: flex; justify-content: space-evenly; gap: 20px; margin-top: 10px;">
                        <div>
                            <label for="idCuenta">Cuenta Origen: </label>
                            <select name="IdOrigen" id="idCuenta" class="custom-select" required>
                                <%
                                    for(CuentaEntity c : empresa.getCuentasByIdempresa()){
                                %>
                                <option  value="<%=c.getIdcuenta()%>"><%=c.getIdcuenta()%> (<%=c.getSaldo()%> <%=c.getDivisaByDivisa().getSimbolo()%>)</option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

                        <div>
                            <img src="https://i.imgur.com/2q9Z5D7.png" width="90px" height="90px;" draggable="false"/>
                        </div>

                        <div>
                            <label for="idCuenta">Cuenta Destino: </label>
                            <input name="IdDestino" type="text" class="form-control" placeholder="Id de la cuenta destino" required/>

                        </div>
                    </div>

                    <button type="submit" class="btn btn-danger mt-5" style="display: flex; margin: auto;">Realizar transferencia</button>
                </form>
            </div>

        </div>

        <div class="mt-4">
            <h1 class="display-5">Transferencias realizadas:</h1>
            <%
                if(transferencias.size() == 0){
            %>
            <div class="alert alert-danger container" role="alert">
                No se ha realizado ninguna transferencia.
            </div>
            <%
                }
            %>
        </div>

    </div>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
