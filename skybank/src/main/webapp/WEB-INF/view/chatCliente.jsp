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
  <title>Title</title>
</head>
<body>
<h1 class="display-2">Hola cliente <%=chat.getNombreCliente()%> <%=chat.getDniUsuario()%>, este es el chat con el asistente <%=chat.getNombreAsistente()%>   <button><a href="/cliente/chatsCliente">Volver a los chats</a></button></h1>


<table border="1" class="table table-sm">
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

</body>
</html>