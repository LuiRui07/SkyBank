<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${empty empresa}">
  <jsp:forward page="/empresa/login" />
</c:if>



<table style="border: 0px; width: 100%; background-color: grey; padding: 10px;">
  <tr>
    <td>Bienvenido, ${empresa.getNombre()} </td>
    <td><a style="float: right; text-decoration: none; color: black;" href="/empresa/logout">Salir</a></td>
  </tr>
</table>