<%@ page import="com.example.skybank.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.skybank.entity.MensajeEntity" %>
<%@ page import="com.example.skybank.dto.Chat" %>
<%@ page import="com.example.skybank.dto.Mensaje" %><%--
  Created by IntelliJ IDEA.
  User: Pablo García Platero
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Chat chat = (Chat) request.getAttribute("chat");
    List<Mensaje> mensajesEntities = (List<Mensaje>) request.getAttribute("mensajes");
%>
<html>
<head>
    <title>Chat privado</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="container">
<h4>Buenas asistente, este es el chat con <%=chat.getNombreCliente()%>, con dni <%=chat.getDniUsuario()%></h4>



<table border="1">
    <td>
        <%
            for(Mensaje mensaje : mensajesEntities){
        %>
        <%=mensaje.getTexto()%> <small style="font-size: 8px"><%=mensaje.gethora()%></small></p>
        <%
            }if(chat.getCerrada()==0){

        %>
    </td>

</table>
<form action="/asistente/crearNuevoMensaje?idconversacion=<%=chat.getIdConversacion()%>" method="post">
    Nuevo mensaje:<div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1">Mensaje</span>
    </div>
    <input name="mensaje" type="text" class="form-control" placeholder="Texto a enviar" aria-label="Username" aria-describedby="basic-addon1">
    <button type="submit">Enviar</button>
</div>
</form>

<%
    }
%>
<a class="btn btn-primary" href="/asistente/chats" role="button">Ver chats</a>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>