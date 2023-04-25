package com.example.skybank.controller;
import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.CustomerRepository;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.OperacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cliente")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/")
    public String getCustomers(Model model, HttpSession session){
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        if (cliente == null){
            return "redirect:/cliente/login";
        } else {
            model.addAttribute("cliente",cliente);
            Set<CuentaEntity> cuentas = cliente.getCuentasByIdCliente();
            model.addAttribute("cuentas",cuentas);
            return "cliente";
        }
    }

    @GetMapping("/login")
    public String loginC(){
        return "loginCliente";
    }

    @GetMapping("/logout")
    public String logout(HttpSession sesion){
        sesion.invalidate();
        return "redirect:/cliente/login";
    }

    @PostMapping("/login")
    public String logear(@RequestParam("DNI") String user, @RequestParam("password") String contra,
                         HttpSession sesion, Model model){
        String urlTo = "redirect:/cliente/";
        ClienteEntity cliente = (ClienteEntity) customerRepository.autenticar(user,contra);
        if(cliente == null){
            model.addAttribute("error", "Usuario no encontrado");
            urlTo = "loginCliente";
        }else{
            sesion.setAttribute("cliente",cliente);
        }
        return urlTo;
    }

    @GetMapping("/register")
    public String registrarCliente (Model model){
        model.addAttribute("cliente",new ClienteEntity());
        return "registerCliente";
    }

    @PostMapping("/crearCliente")
    public String registarClient (@ModelAttribute("cliente") ClienteEntity cliente){
        customerRepository.save(cliente);
        return "redirect:/cliente/login";
    }

    @GetMapping("/historial")
    public String verHistorial (Model model, @RequestParam("id") int IdCuenta){
       CuentaEntity cuenta = cuentaRepository.findById(IdCuenta).orElse(null);
       Set<OperacionEntity> operaciones = cuenta.getOperacionsByIdCuenta();
        model.addAttribute("operaciones",operaciones);
        return "historialCliente";
    }

    @GetMapping("/editar")
    public String mostrarDatos (Model model, @RequestParam("id") int idCliente){
        ClienteEntity cliente = (ClienteEntity) customerRepository.findById(idCliente).orElse(null);
        model.addAttribute("cliente",cliente);
        return "editarCliente";
    }

    @PostMapping("/editar")
    public String doEditar (Model model, @ModelAttribute("cliente") ClienteEntity clienteForm, HttpSession sesion){
        ClienteEntity cliente = customerRepository.findById(clienteForm.getIdCliente()).orElse(null);
        cliente.setNombre(clienteForm.getNombre());
        cliente.setApellido1(clienteForm.getApellido1());
        cliente.setApellido2(clienteForm.getApellido2());
        cliente.setDni(clienteForm.getDni());
        cliente.setCalle(clienteForm.getCalle());
        cliente.setNumero(clienteForm.getNumero());
        cliente.setCiudad(clienteForm.getCiudad());
        cliente.setPais(clienteForm.getPais());
        cliente.setRegion(clienteForm.getRegion());
        cliente.setCp(clienteForm.getCp());
        //cliente.setNacimiento(clienteForm.getNacimiento());
        cliente.setEmail(clienteForm.getEmail());
        customerRepository.save(cliente);
        sesion.setAttribute("cliente",cliente);
        return "redirect:/cliente/";
    }

}
