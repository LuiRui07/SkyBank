package com.example.skybank.controller;

import com.example.skybank.dto.ChatDTO;
import com.example.skybank.dto.MensajeDTO;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.service.AsistenteService;
import com.example.skybank.service.MensajeService;
import com.example.skybank.service.ChatService;
import com.example.skybank.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/asistente")
public class AsistenteController {

    @Autowired
    AsistenteService asistenteService;

    @Autowired
    ChatService chatService;

    @Autowired
    MensajeService mensajeService;
    @GetMapping("/")
    public String mostrarInterfazAsistente(){

        return "asistente/paginaPrincipal";
    }
    @GetMapping("/chats")
    public String mostrarChat(HttpSession session, Model model){
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("usuario");
        List<ChatDTO> chats = this.chatService.listaChatsDeAsistente(cliente.getIdcliente());
        model.addAttribute("chats",chats);
        model.addAttribute("filtro",new FiltroAsistente());
        return "chats";
    }

    @GetMapping("/chat")
    public String mostrarChatPrivado(Model model, @RequestParam("idChat") Integer idChat){
        ChatDTO chat = this.chatService.buscarChat(idChat);
        List<MensajeDTO> mensajesEntities = this.mensajeService.listMensajesPorIdChat(idChat);
        model.addAttribute("chat",chat);
        model.addAttribute("mensajes",mensajesEntities);
        return "asistente/chatPersonal";
    }

    @PostMapping("/crearNuevoMensaje")
    public String agregarMensaje(@RequestParam("mensaje")String mensaje,@RequestParam("idChat") Integer idChat, @RequestParam("idUsuario") Integer idUsuario){
        if(mensaje.equals("")|| mensaje == null){

        }else{
            chatService.agregarMensaje(idChat,mensaje,idUsuario);
        }
        return "redirect:/asistente/chat?idChat=" + idChat;
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroAsistente filtro, HttpSession session, Model model){
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("usuario");
        System.out.println(filtro.getActivo());
        List<ChatDTO> chats = this.chatService.filtrarChats(filtro,cliente);

        model.addAttribute("chats",chats);
        model.addAttribute("filtro",filtro);
        return "/asistente/chats";
    }

    @GetMapping("/limpiar")
    public String doLimpiar(HttpSession session,Model model){
        ClienteEntity usuario = (ClienteEntity) session.getAttribute("usuario");
        List<ChatDTO> chats = this.chatService.listaChatsDeAsistente(usuario.getIdcliente() );
        model.addAttribute("chats",chats);
        model.addAttribute("filtro",new FiltroAsistente());
        return "/asistente/chats";
    }

}
