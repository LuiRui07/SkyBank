package com.example.skybank.service;

import com.example.skybank.dao.DivisaRepository;
import com.example.skybank.dto.Divisa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisaService {

    @Autowired
    private DivisaRepository divisaRepository;

    public List<Divisa> obtenerTodasLasDivisas(){
        return divisaRepository.findAll().stream().map(dEntity -> dEntity.toDTO()).toList();
    }
}
