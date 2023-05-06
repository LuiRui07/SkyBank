/*
@author: Pablo García Platero
*/

package com.example.skybank.service;

import com.example.skybank.dao.AsistenteRepository;
import com.example.skybank.dto.AsistenteDTO;
import com.example.skybank.dto.Cliente;
import com.example.skybank.entity.AsistenteEntity;
import com.example.skybank.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenteService {

    @Autowired
    AsistenteRepository asistenteRepository;

    public AsistenteDTO getAsistenteById (Integer idasistente){
        AsistenteEntity asistente = asistenteRepository.findById(idasistente).orElse(null);
        return asistente == null ? null : asistente.toDTO();
    }

    public AsistenteDTO autenticar (String email, String password){
        AsistenteEntity c = asistenteRepository.autenticar(email,password);
        return c == null ? null : c.toDTO();
    }
}
