package com.example.skybank.controller;

import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
import com.example.skybank.service.AutorizadoService;
import com.example.skybank.service.SocioService;
import com.example.skybank.ui.TipoPersonaEmpresa;
import com.example.skybank.ui.socioOAutorizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/empresa/socios/")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @Autowired
    private AutorizadoService autorizadoService;


    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AutorizadoRepository autorizadoRepository;


    @GetMapping("/")
    public String mostrarSociosYAutorizados(Model model, HttpSession session){
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        if(empresa == null){
            return "redirect:/empresa/login";
        }else{
            model.addAttribute("empresa", empresa);

            List<SocioEntity> socios = socioService.getAllSociosOfEmpresa(empresa);
            model.addAttribute("socios", socios);
            List<AutorizadoEntity> autorizados = autorizadoService.getAllAutorizadosOfEmpresa(empresa);
            model.addAttribute("autorizados", autorizados);

            model.addAttribute("NuevoSocioOAutorizado", new socioOAutorizado(null));

            return "sociosYAutorizados";
        }

    }

    @PostMapping("/crearSocioEmpresa")
    public String crearPrimerSocioEmpresa(@ModelAttribute("socio") SocioEntity socio, @RequestParam("id") Integer idEmpresa){
       EmpresaEntity empresa = empresaRepository.getById(idEmpresa);

       System.out.println(empresa.getNombre() + " " + idEmpresa.toString());
       socio.setEmpresaByIdEmpresa(empresa);
       socioRepository.save(socio);

       System.out.println(empresa.getSociosByIdEmpresa());
       //empresa.getSociosByIdEmpresa().add(socio);
       //empresaRepository.save(empresa);
       return "redirect:/empresa/login";
    }

    @PostMapping("/addSocioOrAutorizado")
    public String addSocioOrAutorizado(@ModelAttribute("NuevoSocioOAutorizado") socioOAutorizado persona, @RequestParam("id") Integer idEmpresa,HttpSession session) {
        EmpresaEntity empresa = empresaRepository.getById(idEmpresa);
        session.setAttribute("empresa",empresa);
        if (persona.getTipo() == TipoPersonaEmpresa.Socio) {
            SocioEntity s = new SocioEntity();
            s.setNombre(persona.getNombre());
            s.setApellido1(persona.getApellido1());
            s.setCalle(persona.getCalle());
            s.setNif(persona.getNif());
            s.setCp(persona.getCp());
            s.setBloqueado(persona.getBloqueado());
            s.setEmpresaByIdEmpresa(empresa);
            s.setFechanacimiento(persona.getFechanacimiento());
            s.setCiudad(persona.getCiudad());
            s.setEmail(persona.getEmail());
            s.setPais(persona.getPais());
            s.setPassword(persona.getPassword());
            socioRepository.save(s);

            empresa.getSociosByIdEmpresa().add(s);
            empresaRepository.save(empresa);
        } else if (persona.getTipo() == TipoPersonaEmpresa.Autorizado) {
            AutorizadoEntity a = new AutorizadoEntity();
            a.setNombre(persona.getNombre());
            a.setApellido1(persona.getApellido1());
            a.setCalle(persona.getCalle());
            a.setNif(persona.getNif());
            a.setCp(persona.getCp());
            a.setBloqueado(persona.getBloqueado());
            a.setEmpresaByIdEmpresa(empresa);
            a.setFechanacimiento(persona.getFechanacimiento());
            a.setCiudad(persona.getCiudad());
            a.setEmail(persona.getEmail());
            a.setPais(persona.getPais());
            a.setPassword(persona.getPassword());

            autorizadoRepository.save(a);

            empresa.getAutorizadosByIdEmpresa().add(a);
            empresaRepository.save(empresa);
        }
        return "redirect:/empresa/socios/";
    }
}
