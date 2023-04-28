package com.example.skybank.service;

import com.example.skybank.dao.SocioRepository;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    public List<SocioEntity> getAllSociosOfEmpresa(EmpresaEntity empresa){
        return socioRepository.todosDeUnaEmpresa(empresa.getIdempresa());
    }

}
