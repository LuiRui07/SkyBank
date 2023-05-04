/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.service;

import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.dto.Autorizado;
import com.example.skybank.dto.Empresa;
import com.example.skybank.dto.Socio;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
import com.example.skybank.ui.TipoPersonaEmpresa;
import com.example.skybank.ui.socioOAutorizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private AutorizadoRepository autorizadoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Socio> getAllSociosOfEmpresa(Empresa empresa){
        return socioRepository.todosDeUnaEmpresa(empresa.getIdempresa()).stream().map(s-> s.toDTO()).toList();
    }

    public List<Socio> getAllSociosOfEmpresaFiltered(Empresa empresa, String filtroDatos){
        return getAllSociosOfEmpresa(empresa).stream().filter(s -> s.getNif().contains(filtroDatos) || (s.getNombre() + " " +s.getApellido1() + " " + s.getApellido2()).contains(filtroDatos)).toList();
    }

    public void crearSocioEmpresa(Socio socio, Integer idEmpresa){
        EmpresaEntity e = empresaRepository.getById(idEmpresa);

        SocioEntity socioEnt = new SocioEntity();

        socioEnt.setNif(socio.getNif());
        socioEnt.setNombre(socio.getNombre());
        socioEnt.setApellido1(socio.getApellido1());
        socioEnt.setApellido2(socio.getApellido2());
        socioEnt.setFechanacimiento(socio.getFechanacimiento());
        socioEnt.setBloqueado(0);
        socioEnt.setEmail(socio.getEmail());
        socioEnt.setPassword(socio.getPassword());
        socioEnt.setCalle(socio.getCalle());
        socioEnt.setNumero(socio.getNumero());
        socioEnt.setPlanta(socio.getPlanta());
        socioEnt.setCiudad(socio.getCiudad());
        socioEnt.setPais(socio.getPais());
        socioEnt.setRegion(socio.getRegion());
        socioEnt.setCp(socio.getCp());
        socioEnt.setSolicituddesbloqueo(0);

        socioEnt.setEmpresaByIdempresa(e);

        socioRepository.save(socioEnt);

        e.getSociosByIdempresa().add(socioEnt);

        empresaRepository.save(e);

    }

    public void addSocioOrAutorizado(socioOAutorizado persona, Integer idEmpresa){
        EmpresaEntity empresa = empresaRepository.getById(idEmpresa);
        if (persona.getTipo() == TipoPersonaEmpresa.Socio) {
            SocioEntity s = new SocioEntity();
            s.setNombre(persona.getNombre());
            s.setApellido1(persona.getApellido1());
            s.setCalle(persona.getCalle());
            s.setNif(persona.getNif());
            s.setCp(persona.getCp());
            s.setBloqueado(persona.getBloqueado());
            s.setEmpresaByIdempresa(empresa);
            s.setFechanacimiento(persona.getFechanacimiento());
            s.setCiudad(persona.getCiudad());
            s.setEmail(persona.getEmail());
            s.setPais(persona.getPais());
            s.setPassword(persona.getPassword());
            s.setSolicituddesbloqueo(0);
            socioRepository.save(s);

            empresa.getSociosByIdempresa().add(s);
            empresaRepository.save(empresa);
        } else if (persona.getTipo() == TipoPersonaEmpresa.Autorizado) {
            AutorizadoEntity a = new AutorizadoEntity();
            a.setNombre(persona.getNombre());
            a.setApellido1(persona.getApellido1());
            a.setCalle(persona.getCalle());
            a.setNif(persona.getNif());
            a.setCp(persona.getCp());
            a.setBloqueado(persona.getBloqueado());
            a.setEmpresaByIdempresa(empresa);
            a.setFechanacimiento(persona.getFechanacimiento());
            a.setCiudad(persona.getCiudad());
            a.setEmail(persona.getEmail());
            a.setPais(persona.getPais());
            a.setPassword(persona.getPassword());
            a.setSolicituddesbloqueo(0);

            autorizadoRepository.save(a);

            empresa.getAutorizadosByIdempresa().add(a);
            empresaRepository.save(empresa);
        }
    }

    public void bloquearSocio(Integer idSocio){
        SocioEntity s = this.socioRepository.getById(idSocio);
        if(s.getBloqueado() == 1){
            s.setBloqueado(0);
        }else if(s.getBloqueado() == 0){
            s.setBloqueado(1);
        }
        socioRepository.save(s);
    }

    public Socio autenticarSocio(String dni, String password){
        SocioEntity socio = socioRepository.autenticar(dni,password);
        return socio == null ? null : socio.toDTO();
    }

    public Empresa obtenerEmpresaSocio(Socio socio){
        SocioEntity s = socioRepository.getById(socio.getId());
        return s.getEmpresaByIdempresa().toDTO();
    }

    public Socio obtenerSocio(Integer idSocio){
        SocioEntity socio = socioRepository.findById(idSocio).orElse(null);
        return socio == null ? null : socio.toDTO();
    }

    public void editarSocio(Socio socio, Integer IdEmpresa){
        EmpresaEntity empresa = empresaRepository.getById(IdEmpresa);
        SocioEntity s = socioRepository.getById(socio.getId());

        s.setId(socio.getId());
        s.setNombre(socio.getNombre());
        s.setApellido1(socio.getApellido1());
        s.setCalle(socio.getCalle());
        s.setNif(socio.getNif());
        s.setCp(socio.getCp());
        s.setBloqueado(socio.getBloqueado());
        s.setEmpresaByIdempresa(empresa);
        s.setFechanacimiento(socio.getFechanacimiento());
        s.setCiudad(socio.getCiudad());
        s.setEmail(socio.getEmail());
        s.setPais(socio.getPais());
        s.setPassword(socio.getPassword());
        s.setSolicituddesbloqueo(socio.getSolicituddesbloqueo());
        socioRepository.save(s);

    }

    public void borrarSocio(Integer idSocio){
        this.socioRepository.deleteById(idSocio);
    }

    public void solicitarDesbloqueo(Integer idSocio){
        SocioEntity a = socioRepository.getById(idSocio);
        a.setSolicituddesbloqueo(1);
        socioRepository.save(a);
    }
}
