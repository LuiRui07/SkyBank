/*
@author: Pablo García Platero
*/

package com.example.skybank.service;

import com.example.skybank.dao.AsistenteRepository;
import com.example.skybank.dao.ChatRepository;
import com.example.skybank.dao.MensajeRepository;
import com.example.skybank.dto.ChatDTO;
import com.example.skybank.entity.AsistenteEntity;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.ConversacionEntity;
import com.example.skybank.entity.MensajeEntity;
import com.example.skybank.ui.FiltroAsistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    AsistenteRepository asistenteRepository;

    @Autowired
    MensajeRepository mensajeRepository;


    public List<ChatDTO> listaChatsDeAsistente(Integer idasistente) {
        List<ConversacionEntity> lista = chatRepository.filtrarChatPorAsistente(idasistente);
        return listaEntidadesADTO(lista);
    }


    public ChatDTO buscarChat(Integer idChat) {
        ConversacionEntity chat = this.chatRepository.getById(idChat);
        return chat.toDTO();
    }

    public void agregarMensaje(Integer idChat, String mensaje) {
        MensajeEntity mensajeEntity = new MensajeEntity();
        ConversacionEntity chat = this.chatRepository.findById(idChat).orElse(null);
        mensajeEntity.setConversacionByIdconversacion(chat);
        mensajeEntity.setTexto(mensaje);
        mensajeEntity.setFecha(new Date(System.currentTimeMillis()));
        mensajeEntity.setHora(new Timestamp(System.currentTimeMillis()));
        this.mensajeRepository.save(mensajeEntity);
    }


    public List<ChatDTO> filtrarChats(FiltroAsistente filtro, ClienteEntity usuario) {
        List<ConversacionEntity> chats;
        if (filtro.getDNI() != null && filtro.getDNI() != "" && filtro.getActivo() != null && filtro.getNombre() != null && filtro.getNombre() != "") {

            chats = chatRepository.filtrarPorTodo(usuario.getIdcliente(), filtro.getActivo(), filtro.getNombre(), filtro.getDNI());
        } else if (filtro.getDNI() != null && filtro.getDNI() != "" && filtro.getActivo() != null) {
            chats = chatRepository.filtrarSinNombre(usuario.getIdcliente(), filtro.getActivo(), filtro.getDNI());
        } else if (filtro.getActivo() != null && filtro.getNombre() != null && filtro.getNombre() != "") {
            chats = chatRepository.filtrarSinDNI(usuario.getIdcliente(), filtro.getActivo(), filtro.getNombre());
        } else if (filtro.getDNI() != null && filtro.getDNI() != "" && filtro.getNombre() != null && filtro.getNombre() != "") {
            chats = chatRepository.filtrarSinCerrado(usuario.getIdcliente(), filtro.getNombre(), filtro.getDNI());
        } else if (filtro.getDNI() != null && filtro.getDNI() != "") {
            chats = chatRepository.filtrarPorSoloDNI(usuario.getIdcliente(), filtro.getDNI());
        } else if (filtro.getActivo() != null) {
            chats = chatRepository.filtrarPorSoloCerrado(usuario.getIdcliente(), filtro.getActivo());
        } else {
            chats = chatRepository.filtrarPorSoloNombre(usuario.getIdcliente(), filtro.getNombre());
        }
        return listaEntidadesADTO(chats);
    }

    protected List<ChatDTO> listaEntidadesADTO(List<ConversacionEntity> lista) {
        ArrayList dtos = new ArrayList<ChatDTO>();

        lista.forEach((final ConversacionEntity cliente) -> dtos.add(cliente.toDTO()));

        return dtos;
    }
}
