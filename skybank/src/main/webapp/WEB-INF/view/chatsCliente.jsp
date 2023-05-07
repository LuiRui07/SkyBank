<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.dto.Chat" %>
<%@ page import="com.example.skybank.dto.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: Pablo García Platero
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Chat> chatEntityList = (List<Chat>) request.getAttribute("chats");
  Cliente cliente = (Cliente) session.getAttribute("cliente");
%>
<html>
<head>
  <title>Lista de chats del cliente</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<h1 class="display-4">Conversaciones </h1>

<table border="1" class="table table-sm">
  <tr>
    <th>Nombre (dni)</th>
    <th>Visitar chat</th>
    <th>Estado</th>
  </tr>
  <%
    for(Chat chat:chatEntityList){
      if (chat.getCerrada() == 0){
  %>
  <tr>
    <td><%=chat.getNombreCliente()%> (<%=chat.getDniUsuario()%>) </td>
    <td><a href="/cliente/chat?idconversacion=<%=chat.getIdConversacion()%>">Ver mensajes</a></td>
    <td><a href="/cliente/cerrar?idconversacion=<%=chat.getIdConversacion()%>">Cerrar</a></td>
  </tr>
  <%
      }
    }
  %>
</table>

<a class="btn btn-primary" role="button" href="/cliente/nuevaConversacion?idcliente=<%=cliente.getIdcliente()%>">Abrir nuevo chat de ayuda</a>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>

