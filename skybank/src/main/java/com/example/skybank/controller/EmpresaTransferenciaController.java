package com.example.skybank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa/transferencias")
public class EmpresaTransferenciaController {

    @GetMapping("")
    public String mostrarTransferencias(){
        return "transferenciasEmpresa";
    }

}
