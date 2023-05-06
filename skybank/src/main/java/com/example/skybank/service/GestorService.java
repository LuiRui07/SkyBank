package com.example.skybank.service;

import com.example.skybank.dao.ClienteRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.GestorRepository;
import com.example.skybank.dto.Cliente;
import com.example.skybank.dto.Empresa;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.Gestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private GestorRepository gestorRepository;

    public com.example.skybank.dto.Gestor autenticar (String dni, String password){
        Gestor g = gestorRepository.autenticar(dni,password);
        return g == null ? null : g.toDTO();
    }

    public List<ClienteEntity> getAllClientes(){
        return clienteRepository.findAll();
    }

    public List<Cliente> getAllClientesDto(){
        return clienteRepository.findAll().stream().map(c -> c.toDTO()).toList();
    }
    public List<EmpresaEntity> getAllEmpresas(){
        return empresaRepository.findAll();
    }

    public List<Empresa> getAllEmpresasDto(){return empresaRepository.findAll().stream().map(e -> e.toDTO()).toList();}

    public List<Cliente> getAllClientesFiltered(String filtroDatos){
        return getAllClientesDto().stream().filter(s -> s.getDni().contains(filtroDatos) || (s.getNombre() + " " +s.getApellido1() + " " + s.getApellido2()).contains(filtroDatos)).toList();
    }

    public List<Empresa> getAllEmpresasFiltered(String filtroDatos){
        return getAllEmpresasDto().stream().filter(s -> s.getCif().contains(filtroDatos) || (s.getNombre()).contains(filtroDatos)).toList();
    }


}
