package com.example.skybank.service;


import com.example.skybank.dao.MensajeRepository;
import com.example.skybank.dto.MensajeDTO;
import com.example.skybank.entity.MensajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    public List<MensajeDTO> listMensajesPorIdChat(Integer idChat){
        List<MensajeEntity> mensajeEntities  = this.mensajeRepository.listaMensajes(idChat);
        return listaEntidadesADTO(mensajeEntities);
    }

    protected List<MensajeDTO> listaEntidadesADTO (List<MensajeEntity> lista) {
        ArrayList dtos = new ArrayList<MensajeDTO>();

        lista.forEach((final MensajeEntity mensajes) -> dtos.add(mensajes.toDTO()));

        return dtos;
    }
}
