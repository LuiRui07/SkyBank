package com.example.skybank.controller;

/*
 * @author Rafael Ceballos
 */

import com.example.skybank.dao.CustomerRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.GestorRepository;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.GestorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorRepository gestorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/")
    public String getListadoCuentas(HttpSession session, Model model){
        GestorEntity gestor = (GestorEntity) session.getAttribute("gestor");

        if (gestor == null){
            return "redirect:/cliente/login";
        }else{
            List<ClienteEntity> listaClientes = customerRepository.findAll();
            List<EmpresaEntity> listaEmpresas = empresaRepository.findAll();

            model.addAttribute("listaEmpresas",listaEmpresas);
            model.addAttribute("listaClientes",listaClientes);
            model.addAttribute("gestor",gestor);
        }
        return "listadoClientes";
    }

    @GetMapping("/login")
    public String loginGestor(){
        return ("loginGestor");
    }

    @PostMapping("/login")
    public String logGestor(@RequestParam("DNI") String user, @RequestParam("password") String pass,
                            HttpSession sesion, Model model){
        String urlTo = "redirect:/gestor/";

        GestorEntity gestor = gestorRepository.autenticar(user,pass);
        if(gestor == null){
            model.addAttribute("error", "Usuario no encontrado");
            urlTo = "loginGestor";
        }else{
            sesion.setAttribute("gestor",gestor);
        }
        return urlTo;
    }

    @GetMapping("/logout")
    public String logout(HttpSession sesion){
        sesion.invalidate();
        return "redirect:/gestor/login";
    }
}