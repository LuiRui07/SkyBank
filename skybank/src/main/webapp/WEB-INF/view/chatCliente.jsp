<%@ page import="com.example.skybank.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.MensajeEntity" %>
<%@ page import="com.example.skybank.dto.ChatDTO" %>
<%@ page import="com.example.skybank.dto.MensajeDTO" %><%--
  Created by IntelliJ IDEA.
  User: Pablo García Platero
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ChatDTO chat = (ChatDTO) request.getAttribute("chat");
  List<MensajeDTO> mensajesEntities = (List<MensajeDTO>) request.getAttribute("mensajes");
%>
<html>
<head>
  <title>Chat Cliente</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<h1>Hola cliente <%=chat.getNombreCliente()%> <%=chat.getDniUsuario()%>, este es el chat con el asistente <%=chat.getNombreAsistente()%></h1>


<table border="1" >
  <td>
    <%
      for(MensajeDTO mensaje : mensajesEntities){
    %>
    <%=mensaje.getTexto()%> <small style="font-size: 8px"><%=mensaje.gethora()%></small></p>
    <%
      }

    %>
  </td>
</table>

<form action="/cliente/crearNuevoMensaje?idconversacion=<%=chat.getIdConversacion()%>" method="post">
  Nuevo mensaje: <input name="mensaje" type="text" >
  <button type="submit">Enviar</button>
</form>

<a class="btn btn-primary" href="/cliente/chatsCliente" role="button">Ver chats</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>