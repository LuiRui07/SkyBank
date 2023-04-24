package com.example.skybank.controller;

import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
import com.example.skybank.service.AutorizadoService;
import com.example.skybank.service.SocioService;
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
}
