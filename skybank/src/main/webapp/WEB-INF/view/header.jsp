<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.skybank.entity.EmpresaEntity" %>
<%@ page import="com.example.skybank.entity.SocioEntity" %>
<%@ page import="com.example.skybank.entity.AutorizadoEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${empty empresa}">
  <jsp:forward page="/empresa/login" />
</c:if>

<%
    String tipoCuenta = (String) session.getAttribute("tipoCuenta");

    Object cuenta = (Object) session.getAttribute("cuenta");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div style="width: 100%; display:flex; align-items: center; justify-content: space-between;">
        <div>
            <a class="navbar-brand" href="/empresa/">Bienvenido a SkyBank: <strong>${empresa.getNombre()}</strong> </a>
            <% if (tipoCuenta != null) { %>
            <p> NIF <%=tipoCuenta.equals("Autorizado") ? " Autorizado: " + ((AutorizadoEntity) cuenta).getNif() : " Socio: " + ((SocioEntity) cuenta).getNif()%></p>
            <% } %>
        </div>
        <ul style="float: right;margin: 0;padding: 0;list-style: none; display: flex; gap: 15px; align-items: center">

            <li>
                <a  class="navbar-text" href="/empresa/">Cuentas</a>
            </li>

            <li>
                <a  class="navbar-text" href="/empresa/transferencias/">Transferencias</a>
            </li>

            <li>
                <a  class="navbar-text" href="/empresa/socios/">Socios/Autorizados</a>
            </li>



            <li>
                <a  class="navbar-text" href="/empresa/datos/">Mi empresa</a>
            </li>

            <%
                if(tipoCuenta != null && tipoCuenta.equals("Autorizado")){
            %>
            <li>
                <a  class="navbar-text" href="/empresa/autorizados/<%=((AutorizadoEntity)cuenta).getId()%>">Mis datos</a>
            </li>
            <%
                }
            %>

            <%
                if(tipoCuenta != null && tipoCuenta.equals("Socio")){
            %>
            <li>
                <a  class="navbar-text" href="/empresa/socios/<%=((SocioEntity)cuenta).getId()%>">Mis datos</a>
            </li>
            <%
                }
            %>

            <li style="margin-left: 10px; font-weight: bold;">
                <a  class="navbar-text" style="color: red;" href="/empresa/logout">Salir</a>
            </li>
        </ul>
    </div>

</nav>

