package com.example.skybank.controller;


import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SocioRepository socioRepository;

    @GetMapping("/")
    public String mostrarEmpresa(Model model,HttpSession sesion){
        EmpresaEntity empresa = (EmpresaEntity) sesion.getAttribute("empresa");
        if(empresa == null){
            return "redirect:/empresa/login";
        }else{
            model.addAttribute("empresa",empresa);
            return "empresa";
        }
    }

    @GetMapping("/register")
    public String registrarEmpresa(Model model){
        model.addAttribute("empresa" , new EmpresaEntity());
        return "registerEmpresa";
    }

    @PostMapping("/crearEmpresa")
    public String registrarEmpresa(@ModelAttribute("empresa") EmpresaEntity empresa){
        System.out.println(empresa.getIdEmpresa());
        empresaRepository.save(empresa);
        return "redirect:/empresa/login";
    }

    @GetMapping("/login")
    public String loginEmpresa(){
        return "loginEmpresa";
    }

    @PostMapping("/login")
    public String String(@RequestParam("nombre") String user, @RequestParam("password") String password
            ,HttpSession sesion,Model modelo){

        String urlTo = "redirect:/empresa/";

        System.out.println(user);
        System.out.println(password);

        EmpresaEntity empresa = (EmpresaEntity) empresaRepository.autenticar(user,password);

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



}
