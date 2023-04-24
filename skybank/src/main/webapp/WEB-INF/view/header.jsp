<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${empty empresa}">
  <jsp:forward page="/empresa/login" />
</c:if>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div style="width: 100%;">
        <a class="navbar-brand" href="/empresa/">Bienvenido, ${empresa.getNombre()} </a>
        <ul style=" float: right;margin: 0;padding: 0;list-style: none; display: flex; gap: 15px; align-items: center">

            <li>
                <a  class="navbar-text" href="/empresa/transferencias/">Transferencias</a>
            </li>

            <li>
                <a  class="navbar-text" href="/empresa/socios/">Socios/Autorizados</a>
            </li>


            <li>
                <a  class="navbar-text" href="/empresa/datos/">Mi cuenta</a>
            </li>

            <li>
                <a  class="navbar-text" style="color: red;" href="/empresa/logout">Salir</a>
            </li>
        </ul>
    </div>

</nav>

