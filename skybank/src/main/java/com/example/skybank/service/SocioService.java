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

    public List<SocioEntity> getAllSociosOfEmpresaFiltered(EmpresaEntity empresa, String filtroDatos){
        return getAllSociosOfEmpresa(empresa).stream().filter(s -> s.getNif().contains(filtroDatos) || (s.getNombre() + " " +s.getApellido1() + " " + s.getApellido2()).contains(filtroDatos)).toList();
    }

}
