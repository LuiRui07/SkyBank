package com.example.skybank.service;

import com.example.skybank.dao.ClienteRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dto.Empresa;
import com.example.skybank.dto.Socio;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<ClienteEntity> getAllClientes(){
        return clienteRepository.findAll();
    }

    public List<EmpresaEntity> getAllEmpresas(){
        return empresaRepository.findAll();
    }

    public List<ClienteEntity> getAllClientesFiltered(String filtroDatos){
        return getAllClientes().stream().filter(s -> s.getDni().contains(filtroDatos) || (s.getNombre() + " " +s.getApellido1() + " " + s.getApellido2()).contains(filtroDatos)).toList();
    }

    public List<EmpresaEntity> getAllEmpresasFiltered(String filtroDatos){
        return getAllEmpresas().stream().filter(s -> s.getCif().contains(filtroDatos) || (s.getNombre()).contains(filtroDatos)).toList();
    }
}
