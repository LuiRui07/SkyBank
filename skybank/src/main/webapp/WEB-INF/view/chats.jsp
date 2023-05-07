<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.dto.Chat" %><%--
  Created by IntelliJ IDEA.
  User: Pablo García Platero
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Chat> chatEntityList = (List<Chat>) request.getAttribute("chats");
%>
<html>
<head>
    <title>Lista chats de clientes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<h1 class="display-4">Conversaciones </h1>
<div align="center">
    <form:form modelAttribute="filtro" method="post" action="/asistente/filtrar">
        <td><h6>Estado: <form:radiobutton path="activo" value="0" label="activo" /> <form:radiobutton path="activo" value="1" label="inactivo" /></h6>
        </td><br/>
        <h6>Email:<form:input path="nombre"></form:input>
            DNI:<form:input path="DNI"></form:input>
            <form:button>Filtrar</form:button>
            <button><a href="/asistente/limpiar" style="text-decoration: none" >Limpiar</a></button>
        </h6>
    </form:form>
</div>


<div class="container" align="center" margin="1rem">
    <table border="1" class="table table-sm">
        <tr class="table-primary">
            <th scope="col">Nombre (dni)</th>
            <th scope="col">Conversaciones</th>
            <th scope="col">Estado</th>
        </tr>
        <%
            for(Chat chat:chatEntityList){
        %>
        <tr class="table-secondary">
            <td><%=chat.getNombreCliente()%> (<%=chat.getDniUsuario()%>) </td>
            <td><a href="/asistente/chat?idconversacion=<%=chat.getIdConversacion()%>">Ver mensajes</a></td>
            <td><%=chat.getCerrada()==1? "Inactivo" : "Activo"%></td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

</html>

