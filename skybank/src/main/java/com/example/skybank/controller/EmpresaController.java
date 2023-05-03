/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.controller;


import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.DivisaRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.dto.Empresa;
import com.example.skybank.dto.Socio;
import com.example.skybank.entity.*;
import com.example.skybank.service.CuentaService;
import com.example.skybank.service.DivisaService;
import com.example.skybank.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private DivisaService divisaService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/")
    public String mostrarEmpresa(Model model,HttpSession sesion){
        Empresa empresa = (Empresa) sesion.getAttribute("empresa");
        if(empresa == null){
            return "redirect:/empresa/login";
        }else{
            model.addAttribute("empresa",empresa);
            model.addAttribute("divisas", divisaService.obtenerTodasLasDivisas());
            model.addAttribute("cuentasEmpresa", empresaService.obtenerCuentasDeEmpresa(empresa));

            return "empresa";
        }
    }

    @GetMapping("/register")
    public String registrarEmpresa(Model model){
        model.addAttribute("empresa" , new Empresa());
        return "registerEmpresa";
    }

    @PostMapping("/crearEmpresa")
    public String registrarEmpresa(@ModelAttribute("empresa") Empresa empresa, Model model){

        Empresa e = empresaService.guardarEmpresa(empresa);

        model.addAttribute("socio",new Socio());
        model.addAttribute("empresa",e);
        return "crearSocioInicial";
    }

    @GetMapping("/login")
    public String loginEmpresa(){

        return "loginEmpresa";
    }

    @PostMapping("/login")
    public String String(@RequestParam("nombre") String user, @RequestParam("password") String password
            ,HttpSession sesion,Model modelo){

        String urlTo = "redirect:/empresa/";


        Empresa empresa = empresaService.autenticarEmpresa(user,password);

        if(empresa == null){
            modelo.addAttribute("error", "Empresa no encontrada");
            urlTo = "loginEmpresa";
        }else{

            if(empresa.getVerificado() == 1){
                sesion.setAttribute("empresa",empresa);
            }else{
                modelo.addAttribute("error", "Empresa no verificada por un Gestor, espere a que sea verificada por favor.");
                urlTo = "loginEmpresa";
            }

        }

        return urlTo;

    }

    @GetMapping("/logout")
    public String logout(HttpSession sesion){
        sesion.invalidate();
        return "redirect:/empresa/login";
    }



    @GetMapping("/datos")
    public String mostrarDatos(Model model, HttpSession session){
        Empresa empresa = (Empresa) session.getAttribute("empresa");

        if(empresa == null){
            return "redirect:/empresa/login";
        }else{
            model.addAttribute("empresa",empresa);
            return "datosEmpresa";
        }
    }

    @PostMapping("/editarEmpresa")
    public String editarEmpresa(@ModelAttribute("empresa") Empresa empresa, HttpSession sesion){
        empresaService.editarEmpresa(empresa);
        sesion.setAttribute("empresa",empresa);

        return "redirect:/empresa/datos";
    }

    @PostMapping("/cambioDivisa")
    public String realizarCambioDivisa(@RequestParam("id") Integer idCuenta,@RequestParam("divisa") Integer idDivisa, HttpSession sesion){

        cuentaService.cambiarDivisa(idCuenta,idDivisa);


        sesion.setAttribute("empresa",empresaService.obtenerEmpresaPorIdCuenta(idCuenta));

        return "redirect:/empresa/";
    }

}
