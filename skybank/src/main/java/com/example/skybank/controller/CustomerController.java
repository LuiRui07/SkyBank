package com.example.skybank.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping("/")
    public String getCustomers(Model model, HttpSession session){
        return "customer";
    }

}
