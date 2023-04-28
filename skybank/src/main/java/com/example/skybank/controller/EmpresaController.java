package com.example.skybank.controller;


import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

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
    public String registrarEmpresa(@ModelAttribute("empresa") EmpresaEntity empresa, Model model){
        empresa.setCuentasByIdEmpresa(new ArrayList<>());
        empresaRepository.save(empresa);
        addCuentaToEmpresa(empresa);

        model.addAttribute("socio",new SocioEntity());
        model.addAttribute("empresa",empresa);
        return "crearSocioInicial";
    }

    private void addCuentaToEmpresa(EmpresaEntity empresa){
        CuentaEntity c = new CuentaEntity();
        c.setEmpresaByIdempresa(empresa);
        cuentaRepository.save(c);

        empresa.getCuentasByIdempresa().add(c);
        empresaRepository.save(empresa);
    }


    @GetMapping("/login")
    public String loginEmpresa(){

        return "loginEmpresa";
    }

    @PostMapping("/login")
    public String String(@RequestParam("nombre") String user, @RequestParam("password") String password
            ,HttpSession sesion,Model modelo){

        String urlTo = "redirect:/empresa/";

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



    @GetMapping("/datos")
    public String mostrarDatos(Model model, HttpSession session){
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");

        if(empresa == null){
            return "redirect:/empresa/login";
        }else{
            model.addAttribute("empresa",empresa);
            return "datosEmpresa";
        }
    }

    @PostMapping("/editarEmpresa")
    public String editarEmpresa(@ModelAttribute("empresa") EmpresaEntity empresa, HttpSession sesion){
        empresaRepository.save(empresa);
        sesion.setAttribute("empresa",empresa);

        return "redirect:/empresa/datos";
    }


}
