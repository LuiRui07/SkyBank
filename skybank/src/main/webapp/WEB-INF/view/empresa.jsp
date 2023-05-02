<%--
  @author: José Luis López Ruiz
--%>

<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.skybank.entity.DivisaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");
    List<DivisaEntity> divisas = (List<DivisaEntity>) request.getAttribute("divisas");

    List<CuentaEntity> cuentas = empresa.getCuentasByIdempresa();
%>

<html>
<head>
    <title>Datos de la Empresa</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

    <jsp:include page="header.jsp" />


    <div class="container">
        <%
            System.out.println(empresa.getSociosByIdempresa());

            if (empresa.getSociosByIdempresa().size() == 0) {

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
            for(CuentaEntity c : cuentas){

        %>
        <p>

            <a class="btn btn-success" data-bs-toggle="collapse" href="#collapse<%=c.getIdcuenta()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                ID Cuenta:  <%=c.getIdcuenta()%>
            </a>

        </p>
        <div class="collapse" id="collapse<%=c.getIdcuenta()%>">
            <div class="card card-body d-flex flex-row align-middle" >
                <div>
                    <p class="display-5">Saldo disponible:</p>
                    <p class="display-1 p-3 rounded bg-light"><%=c.getSaldo()%> <%=c.getDivisaByDivisa().getSimbolo()%></p>
                </div>
                <div class="ml-5 mt-5">
                    <p class="display-4">Divisa: <%=c.getDivisaByDivisa().getNombre()%></p>
                    <p>Cuenta Sospechosa: <%=c.getSospechosa() == 1 ? "Si" : "No" %></p>
                </div>

                <div class="ml-auto mt-5">
                    <button type="button" class="btn btn-success" style="background: url('https://img.freepik.com/vector-gratis/servicio-cambio-moneda-transferencia-monetaria-cambio-dolar-euro-compra-venta-moneda-extranjera-monedas-oro-simbolos-moneda-ue-ee-uu-ilustracion-metafora-concepto-aislado-vector_335657-2818.jpg?w=180'); width: 180px;height: 180px;" data-bs-toggle="modal" data-bs-target="#staticBackdrop<%=c.getIdcuenta()%>">

                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="staticBackdrop<%=c.getIdcuenta()%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <form method="post" action="/empresa/cambioDivisa?id=<%=c.getIdcuenta()%>">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">Realizar un cambio de Divisa</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h3>Tu divisa actual es: <%=c.getDivisaByDivisa().getNombre()%></h3>
                                    <span>Divisa a la que vas a cambiar:</span>
                                        <select name="divisa" class="form-bod" class="custom-select" >
                                            <%
                                                for(DivisaEntity d : divisas){
                                                    if(d.getIddivisa() != c.getDivisaByDivisa().getIddivisa()){
                                            %>


                                            <option label="<%=d.getNombre()%>" value="<%=d.getIddivisa()%>"><%=d.getNombre()%> (<%=d.getValor()%> respecto 1 EUR)</option>
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>

                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-secondary" data-bs-dismiss="modal">Guardar</button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
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
