/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.service;

import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dto.Autorizado;
import com.example.skybank.dto.Empresa;
import com.example.skybank.dto.Socio;
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

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Autorizado> getAllAutorizadosOfEmpresa(Empresa empresa){
        return autorizadoRepository.todosDeUnaEmpresa(empresa.getIdempresa()).stream().map(a -> a.toDTO()).toList();
    }

    public List<Autorizado> getAllAutorizadosOfEmpresaFiltered(Empresa empresa, String filtroDatos){
        return getAllAutorizadosOfEmpresa(empresa).stream().filter(s -> s.getNif().contains(filtroDatos) || (s.getNombre() + " " +s.getApellido1() + " " + s.getApellido2()).contains(filtroDatos)).toList();
    }

    public Autorizado autenticarAutorizado(String dni,String password){
        AutorizadoEntity autorizado = autorizadoRepository.autenticar(dni,password);
        return autorizado == null ? null : autorizado.toDTO();
    }

    public Empresa obtenerEmpresaAutorizado(Autorizado socio){
        AutorizadoEntity s = autorizadoRepository.getById(socio.getId());
        return s.getEmpresaByIdempresa().toDTO();
    }

    public void bloquearAutorizado(Integer idAutorizado){
        AutorizadoEntity s = this.autorizadoRepository.getById(idAutorizado);
        if(s.getBloqueado() == 1){
            s.setBloqueado(0);
        }else if(s.getBloqueado() == 0){
            s.setBloqueado(1);
        }
        autorizadoRepository.save(s);
    }

    public void borrarAutorizado(Integer idAutorizado){
        this.autorizadoRepository.deleteById(idAutorizado);
    }

    public Autorizado obtenerAutorizadoPorId(Integer idAutorizado){
        AutorizadoEntity autorizado = autorizadoRepository.findById(idAutorizado).orElse(null);
        return autorizado == null ? null : autorizado.toDTO();
    }

    public void editarAutorizado(Autorizado autorizado, Integer IdEmpresa){
        EmpresaEntity empresa = empresaRepository.getById(IdEmpresa);
        AutorizadoEntity s = autorizadoRepository.getById(autorizado.getId());

        s.setId(autorizado.getId());
        s.setNombre(autorizado.getNombre());
        s.setApellido1(autorizado.getApellido1());
        s.setCalle(autorizado.getCalle());
        s.setNif(autorizado.getNif());
        s.setCp(autorizado.getCp());
        s.setBloqueado(autorizado.getBloqueado());
        s.setEmpresaByIdempresa(empresa);
        s.setFechanacimiento(autorizado.getFechanacimiento());
        s.setCiudad(autorizado.getCiudad());
        s.setEmail(autorizado.getEmail());
        s.setPais(autorizado.getPais());
        s.setPassword(autorizado.getPassword());
        s.setSolicituddesbloqueo(autorizado.getSolicituddesbloqueo());
        autorizadoRepository.save(s);

    }

    public void solicitarDesbloqueo(Integer idAutorizado){
        AutorizadoEntity a = autorizadoRepository.getById(idAutorizado);
        a.setSolicituddesbloqueo(1);
        autorizadoRepository.save(a);
    }

}
