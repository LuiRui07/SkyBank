/*
    @autor: José Luis López Ruiz
 */


package com.example.skybank.controller;


import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dto.Autorizado;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.service.AutorizadoService;
import com.example.skybank.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/empresa/autorizados/")
public class AutorizadoController {

    @Autowired
    private AutorizadoRepository autorizadoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AutorizadoService autorizadoService;

    @GetMapping("/bloquear")
    public String bloquearAutorizado(@RequestParam("id") Integer idAutorizado, @RequestParam("e") Integer idEmpresa, HttpSession sesion){
        autorizadoService.bloquearAutorizado(idAutorizado);

        sesion.setAttribute("empresa",empresaService.obtenerEmpresaPorIdEmpresa(idEmpresa));
        return "redirect:/empresa/socios/";
    }

    @GetMapping("/borrar")
    public String borrarAutorizado(@RequestParam("id") Integer idAutorizado,@RequestParam("e") Integer idEmpresa, HttpSession sesion){
        autorizadoService.borrarAutorizado(idAutorizado);

        sesion.setAttribute("empresa",empresaService.obtenerEmpresaPorIdEmpresa(idEmpresa));
        return "redirect:/empresa/socios/";
    }


    @GetMapping("/{id}")
    public String mostrarDatosAutorizado(@PathVariable("id") Integer idAutorizado, Model model){

        model.addAttribute("autorizado", autorizadoService.obtenerAutorizadoPorId(idAutorizado));
        return "datosAutorizado";
    }

    @PostMapping("/edit")
    public String editarAutorizado(@ModelAttribute("autorizado") Autorizado autorizado, @RequestParam("eId") Integer empresaId, HttpSession session){

        autorizadoService.editarAutorizado(autorizado,empresaId);

        session.setAttribute("empresa", empresaService.obtenerEmpresaPorIdEmpresa(empresaId));
        session.setAttribute("cuenta", autorizado);
        session.setAttribute("tipoCuenta","Autorizado");

        return "redirect:/empresa/autorizados/" + autorizado.getId();
    }
}
