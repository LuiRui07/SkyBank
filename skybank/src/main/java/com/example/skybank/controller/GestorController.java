package com.example.skybank.controller;

/*
 * @author Rafael Ceballos
 */

import com.example.skybank.dao.ClienteRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.GestorRepository;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.GestorEntity;
import com.example.skybank.service.GestorService;
import com.example.skybank.ui.FiltroListadoClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorRepository gestorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private GestorService gestorService;

    @GetMapping("/")
    public String getListadoCuentas(HttpSession session, Model model){
        GestorEntity gestor = (GestorEntity) session.getAttribute("gestor");

        if (gestor == null){
            return "redirect:/gestor/login";
        }else{
            return filtrarClientesYEmpresas(null,gestor,model);
        }

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

    @GetMapping("/solicitudes")
    public String mostrarSolicitudes(Model model, HttpSession session){
        GestorEntity gestor = (GestorEntity) session.getAttribute("gestor");

        if (gestor == null){
            return "redirect:/gestor/login";
        }else{
            List<ClienteEntity> solicitadas = clienteRepository.getPendientesDeVerificar();
            model.addAttribute("solicitadas",solicitadas);

            List<EmpresaEntity> solicitadasE = empresaRepository.getPendientesVerificar();
            model.addAttribute("solicitadasE", solicitadasE);
        }

        return "solicitudes";
    }

    @GetMapping("/aceptarCliente")
    public String aceptarCliente(Model model, @RequestParam("postId") int idCliente){
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        cliente.setVerificado(1);
        clienteRepository.save(cliente);
        return "redirect:/gestor/solicitudes";
    }

    @GetMapping("/aceptarEmpresa")
    public String aceptarEmpresa(Model model, @RequestParam("postId") int idEmpresa){
        EmpresaEntity empresa = empresaRepository.findById(idEmpresa).orElse(null);

        empresa.setVerificado(1);
        empresaRepository.save(empresa);
        return "redirect:/gestor/solicitudes";
    }

    @PostMapping("/")
    public String doFiltrarClientesYEmpresas(@ModelAttribute("filtro") FiltroListadoClientes filtro, HttpSession session, Model model){
        return filtrarClientesYEmpresas(filtro,(GestorEntity) session.getAttribute("gestor"),model);
    }

    private String filtrarClientesYEmpresas(FiltroListadoClientes filtro, GestorEntity gestor, Model model){
        model.addAttribute("gestor", gestor);

        List<ClienteEntity> listaClientes = new ArrayList<>();
        List<EmpresaEntity> listaEmpresas = new ArrayList<>();

        if(filtro == null || (filtro != null && filtro.getTexto().isEmpty() && filtro.isClientes() && filtro.isEmpresas())){
            FiltroListadoClientes f = new FiltroListadoClientes();
            f.setEmpresas(true);
            f.setClientes(true);

            model.addAttribute("filtro",f);


            listaClientes = clienteRepository.findAll();
            listaEmpresas = empresaRepository.findAll();

        }else if(filtro != null){
            System.out.println(filtro.getTexto() + " a: " + filtro.isEmpresas() + " s: " + filtro.isClientes());

            model.addAttribute("filtro",filtro);

            if(filtro.isEmpresas() && filtro.isClientes()){
                listaClientes = gestorService.getAllClientesFiltered(filtro.getTexto());
                listaEmpresas = gestorService.getAllEmpresasFiltered(filtro.getTexto());
            }else if(filtro.isEmpresas() && !filtro.isClientes()){
                listaEmpresas = gestorService.getAllEmpresasFiltered(filtro.getTexto());
            }else if(!filtro.isEmpresas() && filtro.isClientes()){
                listaClientes = gestorService.getAllClientesFiltered(filtro.getTexto());
            }
        }

        model.addAttribute("listaClientes",listaClientes);
        model.addAttribute("listaEmpresas",listaEmpresas);
        return "listadoClientes";

    }


}