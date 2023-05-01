package com.example.skybank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestor")
public class GestorController {
    @GetMapping("/login")
    public String loginGestor(){
        return ("loginGestor");
    }
}
