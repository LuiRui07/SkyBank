<%--
  @author: Rafael Ceballos Martinez
--%>

<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="com.example.skybank.dto.Cliente" %>
<%@ page import="com.example.skybank.dto.Operacion" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");
    Cliente cliente = (Cliente) request.getAttribute("cliente");

%>
<html>
<head>
    <title><%=cliente.getNombre()%> <%=cliente.getApellido1()%> - GESTIONAR</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container mt-4">
    <h1 class="display-3 mt-5"><%=cliente.getNombre()%> <%=cliente.getApellido1()%> <%=cliente.getApellido2()%></h1>
    <h2>DATOS</h2>

    <table class="table">
        <tbody>
            <tr>
                <td>
                    <strong>DNI: </strong><%=cliente.getDni()%>
                </td>
            </tr>
            <tr>
                <td>
                    <strong>EMAIL: </strong><%=cliente.getEmail()%>
                </td>
            </tr>
            <tr>
                <td>
                    <strong>DIRECCION: </strong>Calle <%=cliente.getCalle()%>, <%=cliente.getNumero()%> (<%=cliente.getCiudad()%>, <%=cliente.getPais()%>)
                </td>
            </tr>
            <tr>
                <td>
                    <strong>FECHA DE NACIMIENTO: </strong><%=cliente.getNacimiento()%>
                </td>
            </tr>

        </tbody>
    </table>


    <a href="historial?postId=<%=cliente.getIdcliente()%>&tipo=0" class="btn btn-primary">Ver historial de operaciones</a>
    <a href="/gestor/" class="btn btn-danger">VOLVER</a>
</div>

</body>
</html>
