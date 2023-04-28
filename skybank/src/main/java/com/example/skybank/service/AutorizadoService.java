package com.example.skybank.service;

import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorizadoService {

    @Autowired
    private AutorizadoRepository autorizadoRepository;

    public List<AutorizadoEntity> getAllAutorizadosOfEmpresa(EmpresaEntity empresa){
        return autorizadoRepository.todosDeUnaEmpresa(empresa.getIdempresa());
    }

}
