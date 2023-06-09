<%--
  @author: José Luis López Ruiz
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="com.example.skybank.dto.Empresa" %>
<%@ page import="com.example.skybank.dto.Operacion" %>
<%@ page import="com.example.skybank.dto.Cuenta" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Empresa empresa = (Empresa) request.getAttribute("empresa");
    List<Cuenta> cuentasEmpresa = (List<Cuenta>) request.getAttribute("cuentasEmpresa");

    List<List<Operacion>> transferenciasRecibidas = (List<List<Operacion>>) request.getAttribute("transferenciasEnviadas");
    List<List<Operacion>> transferenciasEnviadas = (List<List<Operacion>>) request.getAttribute("transferenciasRecibidas");

    Map<Date,Double> gastoFechas = new HashMap<>();

%>

<html>
<head>
    <title>Transferencias</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container mt-3">

        <%
            String error = (String) session.getAttribute("error");
            if (error != null) {

        %>
        <div class="alert alert-danger container" role="alert">
            ${error}
        </div>
        <%
            }
        %>

        <%
            String success = (String) session.getAttribute("success");
            if (success != null) {

        %>
        <div class="alert alert-success container" role="alert">
            ${success}
        </div>
        <%
            }
        %>
        <h1 class="display-5">Últimas transferencias:</h1>

        <form:form modelAttribute="filtro" method="post" action="/empresa/transferencias/filtrado" cssStyle="display: flex; align-items: center; gap: 50px;">
            <div style="display: flex; align-items: center; gap: 20px;">
                <label>ID Cuenta Destino o Concepto: </label>
                <form:input path="filtro" type="text" cssClass="form-control" cssStyle="max-width: 200px !important;"></form:input>
            </div>
            <form:button class="btn btn-danger">Filtrar</form:button>
        </form:form>

        <ul class="nav nav-tabs">
            <li class="nav-item d-flex align-items-center nav-link active" id="navSent" onclick="displaySentTable()">

                <svg focusable="false" width="25px" height="25px" viewBox="0 0 20 20" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                    <path d="M9.98665977,1.1877558 L12.3496437,3.49190192 C12.5964345,3.73035898 12.5964345,4.11697426 12.3496437,4.35543132 C12.1028529,4.59388838 11.702726,4.59388838 11.4559352,4.35543132 L9.98665977,2.93577103 L9.98665977,12.775208 C9.98665977,12.9608796 9.83614324,13.1113961 9.6504717,13.1113961 L9.10003209,13.1113961 C8.91436054,13.1113961 8.76384402,12.9608796 8.76384402,12.775208 L8.76384402,2.93528426 L7.29406483,4.35543132 C7.04727404,4.59388838 6.64714711,4.59388838 6.40035631,4.35543132 C6.15356552,4.11697426 6.15356552,3.73035898 6.40035631,3.49190192 L8.76384402,1.19012683 L8.76384402,1.18558883 C8.76384402,0.999917289 8.91436054,0.849400764 9.10003209,0.849400764 L9.6504717,0.849400764 C9.83614324,0.849400764 9.98665977,0.999917289 9.98665977,1.18558883 L9.98665977,1.1877558 Z M9.37524643,18.75 C8.21903201,18.75 7.28173503,17.7919218 7.28173503,16.6100724 C7.28173503,15.4282231 8.21903201,14.4701449 9.37524643,14.4701449 C10.5314608,14.4701449 11.4687578,15.4282231 11.4687578,16.6100724 C11.4687578,17.7919218 10.5314608,18.75 9.37524643,18.75 Z M9.37524643,17.8328882 C10.0359404,17.8328882 10.5715387,17.2854149 10.5715387,16.6100724 C10.5715387,15.93473 10.0359404,15.3872567 9.37524643,15.3872567 C8.71455247,15.3872567 8.1789542,15.93473 8.1789542,16.6100724 C8.1789542,17.2854149 8.71455247,17.8328882 9.37524643,17.8328882 Z" transform="translate(9.375000, 9.765625) rotate(-330.000000) translate(-9.375000, -9.765625) "></path>
                </svg>
                <a class="text-dark"   >Enviadas</a>

            </li>

            <li class="nav-item d-flex align-items-center nav-link" id="navRecieved" onclick="displayRecievedTable()">


                <svg focusable="false" xmlns="http://www.w3.org/2000/svg" width="25" height="25" viewBox="0 0 25 25">
                    <path fill="#1D1D1B" fill-rule="nonzero" d="M6.417 22.442a.787.787 0 0 1-.196-.105.817.817 0 0 1-.31-.439l-1.224-4.347a.763.763 0 0 1 .553-.958c.426-.106.87.15.99.572l.72 2.515 6.122-10.603a.783.783 0 0 1 1.355.783l-6.12 10.603 2.537-.634c.426-.106.87.15.99.572a.763.763 0 0 1-.552.957l-4.38 1.112a.815.815 0 0 1-.485-.028zM17.75 2.54c1.281.74 1.707 2.401.95 3.712-.756 1.31-2.408 1.772-3.69 1.032-1.281-.74-1.707-2.402-.95-3.712.756-1.31 2.408-1.772 3.69-1.032zm-.587 1.016c-.733-.423-1.677-.159-2.109.59-.432.749-.189 1.698.544 2.121.732.423 1.676.159 2.108-.59.432-.748.19-1.698-.543-2.12z"></path>
                </svg>
                <a class="text-dark" aria-current="page">Recibidas</a>

            </li>

            <li class="nav-item d-flex align-items-center nav-link" id="navChart" onclick="displayChart()">

                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" width="20" height="20" viewBox="0 0 256 256" xml:space="preserve">
                    <defs>
                    </defs>
                        <g style="stroke: none; stroke-width: 0; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: none; fill-rule: nonzero; opacity: 1;" transform="translate(1.4065934065934016 1.4065934065934016) scale(2.81 2.81)">
                            <path d="M 87.994 0 H 69.342 c -1.787 0 -2.682 2.16 -1.418 3.424 l 5.795 5.795 l -33.82 33.82 L 28.056 31.196 l -3.174 -3.174 c -1.074 -1.074 -2.815 -1.074 -3.889 0 L 0.805 48.209 c -1.074 1.074 -1.074 2.815 0 3.889 l 3.174 3.174 c 1.074 1.074 2.815 1.074 3.889 0 l 15.069 -15.069 l 14.994 14.994 c 1.074 1.074 2.815 1.074 3.889 0 l 1.614 -1.614 c 0.083 -0.066 0.17 -0.125 0.247 -0.202 l 37.1 -37.1 l 5.795 5.795 C 87.84 23.34 90 22.445 90 20.658 V 2.006 C 90 0.898 89.102 0 87.994 0 z" style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(0,0,0); fill-rule: nonzero; opacity: 1;" transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                            <path d="M 65.626 37.8 v 49.45 c 0 1.519 1.231 2.75 2.75 2.75 h 8.782 c 1.519 0 2.75 -1.231 2.75 -2.75 V 23.518 L 65.626 37.8 z" style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(0,0,0); fill-rule: nonzero; opacity: 1;" transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                            <path d="M 47.115 56.312 V 87.25 c 0 1.519 1.231 2.75 2.75 2.75 h 8.782 c 1.519 0 2.75 -1.231 2.75 -2.75 V 42.03 L 47.115 56.312 z" style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(0,0,0); fill-rule: nonzero; opacity: 1;" transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                            <path d="M 39.876 60.503 c -1.937 0 -3.757 -0.754 -5.127 -2.124 l -6.146 -6.145 V 87.25 c 0 1.519 1.231 2.75 2.75 2.75 h 8.782 c 1.519 0 2.75 -1.231 2.75 -2.75 V 59.844 C 41.952 60.271 40.933 60.503 39.876 60.503 z" style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(0,0,0); fill-rule: nonzero; opacity: 1;" transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                            <path d="M 22.937 46.567 L 11.051 58.453 c -0.298 0.298 -0.621 0.562 -0.959 0.8 V 87.25 c 0 1.519 1.231 2.75 2.75 2.75 h 8.782 c 1.519 0 2.75 -1.231 2.75 -2.75 V 48.004 L 22.937 46.567 z" style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(0,0,0); fill-rule: nonzero; opacity: 1;" transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                        </g>
                    </svg>
                <a class="text-dark" aria-current="page">Gráfico Gastos</a>

            </li>
        </ul>


            <%
                if(transferenciasRecibidas.size() == 0){
            %>
            <div class="alert alert-danger container" role="alert">
                No se ha recibido ninguna transferencia.
            </div>

            <%
                } else {
            %>
            <table id="tablaRecibidas" class="table hidden" style="overflow-y: scroll !important; max-height: 400px !important; scroll-behavior: smooth;">

            <tr class="table-active">
                <th>CUENTA ORIGEN</th>
                <th>CUENTA DESTINO</th>
                <th>CONCEPTO</th>
                <th>IMPORTE Y ENVÍO</th>
                <th>FECHA</th>
            </tr>

            <%
                    for(List<Operacion> cuenta : transferenciasRecibidas){
                        for(Operacion transferencia : cuenta){
                            Date fecha = transferencia.getFecha();
                            Double gasto = transferencia.getCantidad() * -1;
                            gastoFechas.put(fecha,gastoFechas.getOrDefault(fecha, 0.0) + gasto);
            %>

                <tr>
                    <td>
                        <%=transferencia.getCuentaOrigen().getIdcuenta()%>
                    </td>
                    <td>
                        <%=transferencia.getCuentaDestino().getIdcuenta()%>
                    </td>
                    <td>
                        <%=transferencia.getConcepto()%>
                    </td>

                    <% String cantidadTransferida = transferencia.getCantidad() + "" + transferencia.getDivisa().getSimbolo();
                        if(cantidadTransferida.contains("-")){

                    %>
                        <td class="text-danger font-weight-bold"><%=cantidadTransferida%></td>
                    <%
                        }else{
                    %>

                        <td class="text-success font-weight-bold">+<%=cantidadTransferida%></td>

                    <%
                        }
                    %>

                    <td>
                        <%=transferencia.getFecha().toLocaleString()%>
                    </td>
                </tr>


            <%
                        }
                    }
                }
            %>
            </table>
        <%
            if(transferenciasEnviadas.size() == 0){
        %>

            <div class="alert alert-danger container" role="alert">
                No se ha realizado ninguna transferencia.
            </div>

            <%
            } else {
            %>
            <table id="tablaEnviadas" class="table" style="overflow-y: scroll !important; max-height: 400px !important; scroll-behavior: smooth; display: none;">

            <tr class="table-active">
                <th>CUENTA ORIGEN</th>
                <th>CUENTA DESTINO</th>
                <th>CONCEPTO</th>
                <th>IMPORTE Y ENVÍO</th>
                <th>FECHA</th>
            </tr>

            <%
                for(List<Operacion> cuenta : transferenciasEnviadas){
                    for(Operacion transferencia : cuenta){

            %>

            <tr>
                <td>
                    <%=transferencia.getCuentaOrigen().getIdcuenta()%>
                </td>
                <td>
                    <%=transferencia.getCuentaDestino().getIdcuenta()%>
                </td>
                <td>
                    <%=transferencia.getConcepto()%>
                </td>

                <% String cantidadTransferida = transferencia.getCantidad() + "" + transferencia.getDivisa().getSimbolo();
                    if(cantidadTransferida.contains("-")){

                %>
                <td class="text-danger font-weight-bold"><%=cantidadTransferida%></td>
                <%
                }else{
                %>

                <td class="text-success font-weight-bold">+<%=cantidadTransferida%></td>

                <%
                    }
                %>

                <td>
                    <%=transferencia.getFecha().toLocaleString()%>
                </td>
            </tr>


            <%
                        }
                    }
                }
            %>
        </table>

        <div id="graficaGastos" style="display: none;margin: auto;">
            <canvas id="myChart" style="width:100%;font-family: Calibri;"></canvas>

        </div>


        </div>


    <p class="container" style="display: flex; align-items: center; justify-content: center;">
        <button class="btn btn-danger" type="button" data-bs-toggle="collapse" data-bs-target="#collapseNuevaTransferencia" aria-expanded="false" aria-controls="collapseNuevaTransferencia">
            Realizar una nueva transferencia
        </button>
    </p>
    <div style="padding: 20px;">
        <div class="collapse" id="collapseNuevaTransferencia">
            <div class="container card" style="margin-top: 40px !important;">
                <div class="card-title text-center">
                    <h2 class="display-5 mt-3">Realizar una nueva transferencia</h2>
                </div>
                <div class="card-body">


                    <form method="post" action="/empresa/transferencias/nueva">
                        <div style="display: flex; gap: 10px; align-items: center; justify-content: center;">
                            <label for="cantidad">Cantidad a transferir: </label>
                            <input name="cantidad" id="cantidad" type="number" min="1" step="0.01" class="form-control"  style="width: 100px; height: fit-content;" required/>
                        </div>
                        <div class="container" style="width: 50%;">
                            <label for="concepto">Concepto: </label>
                            <textarea name="concepto" id="concepto" class="form-control" style="width: 100% !important;" ></textarea>
                        </div>

                        <div style="display: flex; justify-content: space-evenly; gap: 20px; margin-top: 10px;">
                            <div>
                                <label for="idCuenta">Cuenta Origen: </label>
                                <select name="IdOrigen" id="idCuenta" class="custom-select" required>
                                    <%
                                        for(Cuenta c : cuentasEmpresa){
                                    %>
                                    <option  value="<%=c.getIdcuenta()%>"><%=c.getIdcuenta()%> (<%=c.getSaldo()%> <%=c.getDivisa().getSimbolo()%>)</option>
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
        </div>
    </div>


    </div>

    <script>
        const tablaRecibidas = document.getElementById("tablaRecibidas");
        const tablaEnviadas = document.getElementById("tablaEnviadas");
        const chartDiv = document.getElementById("graficaGastos");

        const navRecieved = document.getElementById("navRecieved");
        const navSent = document.getElementById("navSent");
        const navChart = document.getElementById("navChart");

        const displaySentTable = () => {
            tablaEnviadas.style.display = 'none';
            tablaRecibidas.style.display = 'table';

            navRecieved.classList.remove("active");
            navSent.classList.add("active");

            navChart.classList.remove("active");
            chartDiv.style.display = 'none';
        }

        const displayRecievedTable = () => {
            tablaEnviadas.style.display = 'table';
            tablaRecibidas.style.display = 'none';
            navRecieved.classList.add("active");
            navSent.classList.remove("active");

            navChart.classList.remove("active");
            chartDiv.style.display = 'none';
        }



    </script>

    <script>
        const displayChart = () => {
            tablaEnviadas.style.display = 'none';
            tablaRecibidas.style.display = 'none';
            navRecieved.classList.remove("active");
            navSent.classList.remove("active");

            navChart.classList.add("active");
            chartDiv.style.display = 'block';

            createChart();
        }

        const createChart = () => {
            const fechasGrafica = [];
            const dineroFecha = [];

            <%
                    for(Date d : gastoFechas.keySet()){
            %>
                fechasGrafica.push("<%=d.toString()%>")
                dineroFecha.push("<%=gastoFechas.get(d)%>")
            <%
                }
            %>

            new Chart("myChart", {
                type: "line",
                data: {
                    labels: fechasGrafica.reverse(),
                    datasets: [{
                        data: dineroFecha.reverse(),
                        borderColor: "red",
                        fill: false,
                        backgroundColor:[
                            "red"
                        ]
                    }]
                },
                options: {
                    responsive: true,
                    legend: {display: false}

                }
            });

        }
    </script>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
