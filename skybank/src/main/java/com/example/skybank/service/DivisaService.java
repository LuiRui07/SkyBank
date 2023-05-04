/*
    @autor: José Luis López Ruiz(25%) y Luis Ruiz Nuñez(75%)
 */

package com.example.skybank.service;

import com.example.skybank.dao.DivisaRepository;
import com.example.skybank.dto.Divisa;
import com.example.skybank.entity.DivisaEntity;
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

    public List<Divisa> obtenerTodasLasDivisasMenos(Divisa divisa){
        return divisaRepository.getDivisasMenos(divisa.getIddivisa()).stream().map(d -> d.toDTO()).toList();
    }

    public Double obtenerValorDivisa(int divisa){
        DivisaEntity div = divisaRepository.findById(divisa).orElse(null);
        return div.getValor();
    }

    public Divisa obtenerDivisa(int divisa){
        DivisaEntity div = divisaRepository.findById(divisa).orElse(null);
        return div == null ? null : div.toDTO();
    }
}
