/*
@author: Luis Ruiz Nuñez
*/
package com.example.skybank.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class InicioController {

    @GetMapping("/")
    public String doMostrar(Model model, HttpSession session){
        return "inicio";
    }
}
