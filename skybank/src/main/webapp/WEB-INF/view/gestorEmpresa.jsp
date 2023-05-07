<%@ page import="com.example.skybank.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.ClienteEntity" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.dto.Operacion" %>
<%@ page import="com.example.skybank.dto.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 05/05/2023
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");
  Empresa e = (Empresa) request.getAttribute("empresa");
%>
<html>
<head>
  <title><%=e.getNombre()%> - GESTIONAR</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container mt-4">
  <h1 class="display-3 mt-5"><%=e.getNombre()%></h1>
  <h2>DATOS</h2>

  <table class="table">
    <tbody>
      <tr>
        <td>
          <strong>CIF: </strong><%=e.getCif()%>
        </td>
      </tr>
      <tr>
        <td>
          <strong>EMAIL: </strong><%=e.getEmailcorporativo()%>
        </td>
      </tr>
    <tr>
      <td>
        <strong>DIRECCION: </strong>Calle <%=e.getCalle()%>, <%=e.getNumero()%> (<%=e.getCiudad()%>, <%=e.getPais()%>)
      </td>
    </tr>
    </tbody>
  </table>
<a href="historialEmpresa?postId=<%=e.getIdempresa()%>&tipo=1" class="btn btn-primary">Ver historial de operaciones</a>
<a href="/gestor/" class="btn btn-danger">VOLVER</a>
</div>
</body>
</html>
