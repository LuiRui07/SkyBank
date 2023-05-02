package com.example.skybank.controller;

import com.example.skybank.entity.ClienteEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestor")
public class GestorController {
    @GetMapping("/login")
    public String loginGestor(){
        return ("loginGestor");
    }

    @PostMapping("/login")
    public String logGestor(@RequestParam("nombre") String user, @RequestParam("password") String pass,
                         HttpSession sesion, Model model){
        String urlTo = "redirect:/gestor/";


        return urlTo;
    }
}