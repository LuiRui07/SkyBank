/*
@author: Pablo García Platero
*/

package com.example.skybank.controller;

import com.example.skybank.dao.AsistenteRepository;
import com.example.skybank.dto.ChatDTO;
import com.example.skybank.dto.Cliente;
import com.example.skybank.dto.MensajeDTO;
import com.example.skybank.entity.AsistenteEntity;
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
    AsistenteRepository asistenteRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    MensajeService mensajeService;
    @GetMapping("/")
    public String mostrarInterfazAsistente(){

        return "/asistentePaginaPrincipal";
    }

    @GetMapping("/login")
    public String loginC(){
        return "asistenteLogin";
    }

    @PostMapping("/login")
    public String logear(@RequestParam("email") String user, @RequestParam("password") String contra, HttpSession sesion, Model model){
        String urlTo = "redirect:/asistente/";
        AsistenteEntity asistente = this.asistenteRepository.autenticar(user,contra);
        if(asistente == null){
            model.addAttribute("error", "asistente no encontrado");
            sesion.setAttribute("asistente",asistente);
            urlTo = "asistenteLogin";
        }else{
                sesion.setAttribute("asistente",asistente);
            }
            return urlTo;
    }


    @GetMapping("/chats")
    public String mostrarChat(HttpSession session, Model model){
        AsistenteEntity asistente = (AsistenteEntity) session.getAttribute("asistente");
        List<ChatDTO> chats = this.chatService.listaChatsDeAsistente(asistente.getIdasistente());
        model.addAttribute("chats",chats);
        model.addAttribute("filtro",new FiltroAsistente());
        return "chats";
    }

    @GetMapping("/chat")
    public String mostrarChatPrivado(Model model, @RequestParam("idconversacion") Integer idChat){
        ChatDTO chat = this.chatService.buscarChat(idChat);
        List<MensajeDTO> mensajesEntities = this.mensajeService.listMensajesPorIdChat(idChat);

        model.addAttribute("chat",chat);
        model.addAttribute("mensajes",mensajesEntities);
        return "chatPersonal";
    }

    @PostMapping("/crearNuevoMensaje")
    public String agregarMensaje(@RequestParam("mensaje")String mensaje,@RequestParam("idconversacion") Integer idChat){
        if(mensaje.equals("")|| mensaje == null){

        }else{
            mensaje = "Asistente: " + mensaje;
            chatService.agregarMensaje(idChat,mensaje);
        }
        return "redirect:/asistente/chat?idconversacion=" + idChat;
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroAsistente filtro, HttpSession session, Model model){
        AsistenteEntity asistente = (AsistenteEntity) session.getAttribute("asistente");
        System.out.println(filtro.getActivo());
        List<ChatDTO> chats = this.chatService.filtrarChats(filtro,asistente);
        model.addAttribute("chats",chats);
        model.addAttribute("filtro",filtro);
        return "chats";
    }

    @GetMapping("/limpiar")
    public String doLimpiar(HttpSession session,Model model){
        AsistenteEntity asistenteEntity = (AsistenteEntity) session.getAttribute("asistente");
        List<ChatDTO> chats = this.chatService.listaChatsDeAsistente(asistenteEntity.getIdasistente());
        model.addAttribute("chats",chats);
        model.addAttribute("filtro",new FiltroAsistente());
        return "chats";
    }

}
