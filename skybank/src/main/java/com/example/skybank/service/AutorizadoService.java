/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.service;

import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
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

    public List<AutorizadoEntity> getAllAutorizadosOfEmpresaFiltered(EmpresaEntity empresa, String filtroDatos){
        return getAllAutorizadosOfEmpresa(empresa).stream().filter(s -> s.getNif().contains(filtroDatos) || (s.getNombre() + " " +s.getApellido1() + " " + s.getApellido2()).contains(filtroDatos)).toList();
    }

}
