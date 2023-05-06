<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.skybank.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.dto.ChatDTO" %><%--
  Created by IntelliJ IDEA.
  User: Pablo García Platero
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<ChatDTO> chatEntityList = (List<ChatDTO>) request.getAttribute("chats");
%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>Conversaciones </h1>

<table border="1">
  <tr>
    <th>Nombre (dni)</th>
    <th>Visitar chat</th>
    <th>Estado</th>
  </tr>
  <%
    for(ChatDTO chat:chatEntityList){
  %>
  <tr>
    <td><%=chat.getNombreCliente()%> (<%=chat.getDniUsuario()%>) </td>
    <td><a href="/asistente/chat?idconversacion=<%=chat.getIdConversacion()%>">Ver mensajes</a></td>
    <td><%=chat.getCerrada()==1? "Inactivo" : "Activo"%></td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>

