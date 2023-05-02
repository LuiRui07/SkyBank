package com.example.skybank.service;

import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.DivisaRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dto.Empresa;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.DivisaEntity;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private DivisaRepository divisaRepository;

    public void guardarEmpresa(Empresa empresaDTO){
        EmpresaEntity nuevaEmpresa = new EmpresaEntity();
        nuevaEmpresa.setIdempresa(empresaDTO.getIdempresa());
        nuevaEmpresa.setCif(empresaDTO.getCif());
        nuevaEmpresa.setNombre(empresaDTO.getNombre());
        nuevaEmpresa.setEmailcorporativo(empresaDTO.getEmailcorporativo());
        nuevaEmpresa.setPasswordempresa(empresaDTO.getPasswordempresa());
        nuevaEmpresa.setCalle(empresaDTO.getCalle());
        nuevaEmpresa.setNumero(empresaDTO.getNumero());
        nuevaEmpresa.setPlanta(empresaDTO.getPlanta());
        nuevaEmpresa.setCiudad(empresaDTO.getCiudad());
        nuevaEmpresa.setPais(empresaDTO.getPais());
        nuevaEmpresa.setRegion(empresaDTO.getRegion());
        nuevaEmpresa.setCp(empresaDTO.getCp());
        nuevaEmpresa.setVerificado(empresaDTO.getVerificado());

        CuentaEntity c = new CuentaEntity();
        DivisaEntity d = divisaRepository.getById(1);

        c.setDivisaByDivisa(d);
        c.setEmpresaByIdempresa(nuevaEmpresa);
        cuentaRepository.save(c);

        ArrayList<CuentaEntity> cuentas = new ArrayList<>();
        cuentas.add(c);

        nuevaEmpresa.setCuentasByIdempresa(cuentas);

        empresaRepository.save(nuevaEmpresa);

    }

}
