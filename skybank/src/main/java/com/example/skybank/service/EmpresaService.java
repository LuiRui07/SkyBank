/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.service;

import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.DivisaRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dto.Cliente;
import com.example.skybank.dto.Cuenta;
import com.example.skybank.dto.Empresa;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.DivisaEntity;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private DivisaRepository divisaRepository;

    public Empresa guardarEmpresa(Empresa empresaDTO){
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
        nuevaEmpresa.setBloqueada(empresaDTO.getBloqueada());

        empresaRepository.save(nuevaEmpresa);

        CuentaEntity c = new CuentaEntity();
        DivisaEntity d = divisaRepository.getById(1);

        c.setDivisaByDivisa(d);
        c.setEmpresaByIdempresa(nuevaEmpresa);
        cuentaRepository.save(c);

        ArrayList<CuentaEntity> cuentas = new ArrayList<>();
        cuentas.add(c);

        nuevaEmpresa.setCuentasByIdempresa(cuentas);

        empresaRepository.save(nuevaEmpresa);

        return nuevaEmpresa.toDTO();
    }

    public void editarEmpresa(Empresa empresaDTO){

        EmpresaEntity e = empresaRepository.getById(empresaDTO.getIdempresa());

        e.setCif(empresaDTO.getCif());
        e.setNombre(empresaDTO.getNombre());
        e.setEmailcorporativo(empresaDTO.getEmailcorporativo());
        e.setPasswordempresa(empresaDTO.getPasswordempresa());
        e.setCalle(empresaDTO.getCalle());
        e.setNumero(empresaDTO.getNumero());
        e.setPlanta(empresaDTO.getPlanta());
        e.setCiudad(empresaDTO.getCiudad());
        e.setPais(empresaDTO.getPais());
        e.setRegion(empresaDTO.getRegion());
        e.setCp(empresaDTO.getCp());
        e.setVerificado(empresaDTO.getVerificado());
        e.setBloqueada(empresaDTO.getBloqueada());

        empresaRepository.save(e);
    }


    public Empresa autenticarEmpresa(String nif,String password){
        EmpresaEntity e = empresaRepository.autenticar(nif,password);
        return e == null ? null : e.toDTO();
    }

    public Empresa obtenerEmpresaPorIdCuenta(Integer idCuenta){
        CuentaEntity c = cuentaRepository.findById(idCuenta).orElse(null);
        return c == null ? null : c.getEmpresaByIdempresa().toDTO();
    }

    public Empresa obtenerEmpresaPorIdEmpresa(Integer idEmpresa){
        EmpresaEntity e = empresaRepository.findById(idEmpresa).orElse(null);
        return e == null ? null : e.toDTO();
    }


    public List<Cuenta> obtenerCuentasDeEmpresa(Empresa empresa){
        EmpresaEntity e = empresaRepository.getById(empresa.getIdempresa());
        System.out.println(e.getCuentasByIdempresa());
        List<Cuenta> cuentas = e.getCuentasByIdempresa().stream().map(c -> c.toDTO()).toList();
        System.out.println(cuentas);
        return cuentas;
    }

    public List<Empresa> obtenerTodasLasEmpresas(){
        List<Empresa> empresas = empresaRepository.findAll().stream().map(e -> e.toDTO()).toList();
        return empresas;
    }

    public Empresa getEmpresaById (Integer idempresa){

        EmpresaEntity e = empresaRepository.findById(idempresa).orElse(null);
        return e == null ? null : e.toDTO();
    }

    public Empresa getEmpresaByCuentaId (Integer idCuenta){
        CuentaEntity cuenta = cuentaRepository.getById(idCuenta);
        EmpresaEntity e = cuenta.getEmpresaByIdempresa();
        return e==null ? null : e.toDTO();
    }

    public List<Empresa> getPendientesVerificacion(){
        return empresaRepository.getPendientesVerificar().stream().map(c -> c.toDTO()).toList();
    }

}
