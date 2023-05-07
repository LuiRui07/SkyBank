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
  <title>Chat Cliente</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="container" style="margin-top: 3%;">
<h1 style="margin-bottom: 2%;">Chat con el Asistente: <%=chat.getNombreAsistente()%>  </h1>


    <%
      for(Mensaje mensaje : mensajesEntities){
    %>
    <%
        if (!mensaje.getTexto().contains("Asistente")) { %>
            <div class="d-flex justify-content-start mb-2">
                <div class="p-3 ms-3" style="border-radius: 15px; background-color: rgba(57, 192, 237,.2); margin-bottom: 2%">
                    <p class="small mb-0"><%=mensaje.getTexto()%>    <footer class="blockquote-footer" style="font-size: 8px;"><%=mensaje.gethora().toLocaleString()%></footer></p>
                </div>
            </div>
        <%} else {%>
            <div class="d-flex flex-row justify-content-end mb-2">
                <div class="p-3 ms-3 border" style="border-radius: 15px; background-color: #fbfbfb; margin-bottom: 2%">
                    <p class="small mb-0"><%=mensaje.getTexto()%>    <footer class="blockquote-footer" style="font-size: 8px;"><%=mensaje.gethora().toLocaleString()%></footer></p>
                </div>
            </div>
        <%}%>

    <%
      }
    %>

<form action="/cliente/crearNuevoMensaje?idconversacion=<%=chat.getIdConversacion()%>" method="post">
    <div class="input-group">
        <input class="form-control"  style="width: 90%" placeholder="Nuevo Mensaje" name="mensaje" type="text">
        <button class="btn btn-primary" type="submit" style="margin-left: 1%">Enviar</button>
    </div>
</form>

<a class="btn btn-danger" href="/cliente/chatsCliente" style="float:left; margin-bottom: 1%" role="button">Volver</a>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</body>
</html>