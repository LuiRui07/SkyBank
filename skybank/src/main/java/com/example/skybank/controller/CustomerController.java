package com.example.skybank.controller;
import com.example.skybank.dao.*;
import com.example.skybank.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cliente")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private TipoOperacionRepository tipoOperacionRepository;

    @Autowired
    private DivisaRepository divisaRepository;

    @GetMapping("/")
    public String getCustomers(Model model, HttpSession session){
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        if (cliente == null){
            return "redirect:/cliente/login";
        } else {
            model.addAttribute("cliente",cliente);
            List<CuentaEntity> cuentas = cliente.getCuentasByIdcliente();
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
        model.addAttribute("clienteNuevo",new ClienteEntity());
        return "registerCliente";
    }

    @PostMapping("/crearCliente")
    public String registarClient (@ModelAttribute("clienteNuevo") ClienteEntity cliente){
        customerRepository.save(cliente);
        return "redirect:/cliente/login";
    }

    @GetMapping("/historial")
    public String verHistorial (Model model, @RequestParam("id") int id){
       CuentaEntity cuenta = cuentaRepository.findById(id).orElse(null);
       List<OperacionEntity> operaciones =  operacionRepository.findbyAccount(cuenta.getIdcuenta());
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
        ClienteEntity cliente = customerRepository.findById(clienteForm.getIdcliente()).orElse(null);
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
        //cliente.setNacimiento(clienteForm.getNacimiento()); FALTA
        cliente.setEmail(clienteForm.getEmail());
        customerRepository.save(cliente);
        sesion.setAttribute("cliente",cliente);
        return "redirect:/cliente/";
    }

    @GetMapping("/trans")
    public String doTrans (Model model, @RequestParam("id") int idCuenta){
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        List<CuentaEntity> cuentas = cuentaRepository.findAll();
        OperacionEntity operacion = new OperacionEntity();
        model.addAttribute("cuentaOrigen",cuenta);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("operacion",operacion);
        return "clienteTransf";
    }

    @PostMapping("/realizarTransf")
    public String realizarTrans (Model model, @ModelAttribute("operacion") OperacionEntity operacion, @ModelAttribute("cuentaOrigen") CuentaEntity cuenta){
        //Not working
        return "redirect:/cliente/";
    }

    @GetMapping("/cambio")
    public String mostrarCambio(Model model, @RequestParam("id") int idCuenta){
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        OperacionEntity operacion = new OperacionEntity();
        List<DivisaEntity> divisas = (List<DivisaEntity>) divisaRepository.findAll();
        model.addAttribute("cuentaCambio",cuenta);
        model.addAttribute("operacionCambio",operacion);
        model.addAttribute("divisas",divisas);
        return "clienteCambio";
    }

}
