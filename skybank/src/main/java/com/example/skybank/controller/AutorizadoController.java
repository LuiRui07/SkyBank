package com.example.skybank.controller;


import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.SocioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/empresa/autorizados/")
public class AutorizadoController {

    @Autowired
    private AutorizadoRepository autorizadoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/bloquear")
    public String bloquearAutorizado(@RequestParam("id") Integer idAutorizado, @RequestParam("e") Integer idEmpresa, HttpSession sesion){
        AutorizadoEntity s = this.autorizadoRepository.getById(idAutorizado);

        sesion.setAttribute("empresa",empresaRepository.getById(idEmpresa));

        if(s.getBloqueado() == 1){
            s.setBloqueado(0);
        }else if(s.getBloqueado() == 0){
            s.setBloqueado(1);
        }
        autorizadoRepository.save(s);

        return "redirect:/empresa/socios/";
    }

    @GetMapping("/borrar")
    public String borrarAutorizado(@RequestParam("id") Integer idAutorizado,@RequestParam("e") Integer idEmpresa, HttpSession sesion){
        this.autorizadoRepository.deleteById(idAutorizado);

        sesion.setAttribute("empresa",empresaRepository.getById(idEmpresa));


        return "redirect:/empresa/socios/";
    }

}
