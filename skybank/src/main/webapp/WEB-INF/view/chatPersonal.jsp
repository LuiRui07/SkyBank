<%@ page import="com.example.skybank.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.MensajeEntity" %>
<%@ page import="com.example.skybank.dto.ChatDTO" %>
<%@ page import="com.example.skybank.dto.MensajeDTO" %><%--
  Created by IntelliJ IDEA.
  User: martin
  Date: 27/03/2023
  Time: 10:49
  To change this template use File | Settings | File Templates.
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
<h1>Hola asistente del sistema, este es el chat con <%=chat.getNombreCliente()%> con dni <%=chat.getDniUsuario()%> <button><a href="/asistente/chats">Volver a los chats</a></button></h1>


<table>
    <%
        for(MensajeDTO mensaje : mensajesEntities){
    %>
    <%=mensaje.getTexto()%> <small style="font-size: 8px"><%=mensaje.gethora()%></small></p>
    <%
        }if(chat.getCerrada()==0){

    %>
    <form action="/asistente/crearNuevoMensaje?idconversacion=<%=chat.getIdConversacion()%>&idcliente=<%=chat.getIdAsistente()%>" method="post">
        Nuevo mensaje: <input name="mensaje" type="text" >
        <button type="submit">Enviar</button>
    </form>
    <%
        }
    %>
    </table>

</body>
</html>
