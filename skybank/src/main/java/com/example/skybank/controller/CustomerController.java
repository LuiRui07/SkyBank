/*
@author: Luis Ruiz Nuñez
*/

package com.example.skybank.controller;
import com.example.skybank.dao.*;
import com.example.skybank.entity.*;
import com.example.skybank.ui.FiltroOperaciones;
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
            List<CuentaEntity> cuentas = cuentaRepository.findByCliente(cliente.getIdcliente());
            model.addAttribute("cuentas",cuentas);
            return "cliente";
        }
    }

    @GetMapping("/login")
    public String loginC(){
        return "clienteLogin";
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
        return "clienteRegister";
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
       FiltroOperaciones filtro = new FiltroOperaciones();
       List<TipoOperacionEntity> tipos = tipoOperacionRepository.findAll();
       model.addAttribute("tipos",tipos);
       model.addAttribute("filtro",filtro);
       model.addAttribute("operaciones",operaciones);
       model.addAttribute("cuenta",cuenta);
       return "clienteHistorial";
    }

    @PostMapping ("/filtrar")
    public String doFiltrar(Model model, @ModelAttribute("filtro") FiltroOperaciones filtro){
        List<OperacionEntity> operaciones = operacionRepository.filtrarPorTipo(filtro.getTipo());
        model.addAttribute("operaciones", operaciones);
        return "clienteHistorial";
    }

    @GetMapping("/editar")
    public String mostrarDatos (Model model, @RequestParam("id") int idCliente){
        ClienteEntity cliente = (ClienteEntity) customerRepository.findById(idCliente).orElse(null);
        model.addAttribute("cliente",cliente);
        return "clienteEditar";
    }

    @PostMapping("/editar")
    public String doEditar (Model model, @ModelAttribute("cliente") ClienteEntity clienteForm, HttpSession sesion){
        ClienteEntity cliente = customerRepository.findById(clienteForm.getIdcliente()).orElse(null);
        cliente.setNombre(clienteForm.getNombre());
        cliente.setApellido1(clienteForm.getApellido1());
        cliente.setApellido2(clienteForm.getApellido2());
        cliente.setDni(clienteForm.getDni());
        cliente.setNacimiento(clienteForm.getNacimiento());
        cliente.setEmail(clienteForm.getEmail());
        cliente.setCalle(clienteForm.getCalle());
        cliente.setNumero(clienteForm.getNumero());
        cliente.setPlanta(clienteForm.getPlanta());
        cliente.setCp(clienteForm.getCp());
        cliente.setCiudad(clienteForm.getCiudad());
        cliente.setRegion(clienteForm.getRegion());
        cliente.setPais(clienteForm.getPais());

        customerRepository.save(cliente);
        sesion.setAttribute("cliente",cliente);
        return "redirect:/cliente/";
    }

    @GetMapping("/trans")
    public String doTrans (Model model, @RequestParam("id") int idCuenta){
        CuentaEntity cuentaOrigen = cuentaRepository.findById(idCuenta).orElse(null);
        List<CuentaEntity> cuentas = cuentaRepository.findByDivisa(cuentaOrigen.getDivisaByDivisa().getIddivisa());
        cuentas.remove(cuentaOrigen);
        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByIdcuenta(cuentaOrigen);
        operacion.setDivisaByDivisa(cuentaOrigen.getDivisaByDivisa());
        model.addAttribute("cuentaOrigen",cuentaOrigen);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("operacion",operacion);
        return "clienteTransf";
    }

    @PostMapping("/doTransf")
    public String realizarTrans (Model model, @ModelAttribute("operacion") OperacionEntity operacionForm){
        if (operacionForm.getCantidad() > operacionForm.getCuentaByIdcuenta().getSaldo() || operacionForm.getCantidad() == 0.00){
            return "clienteError";
        } else {
            TipoOperacionEntity tipo = tipoOperacionRepository.findById(1).orElse(null);
            OperacionEntity op = new OperacionEntity();
            op.setCantidad(operacionForm.getCantidad());
            op.setConcepto(null);
            op.setCuentaByIdcuenta(operacionForm.getCuentaByIdcuenta());
            op.setDivisaByDivisa(operacionForm.getDivisaByDivisa());
            op.setCuentaByIdcuenta2(operacionForm.getCuentaByIdcuenta2());
            op.setTipoOperacionByTipopperacionid(tipo);
            op.setConcepto(operacionForm.getConcepto());
            op.setFecha(new Date());

            ClienteEntity cliente = operacionForm.getCuentaByIdcuenta2().getClienteByIdcliente();
            CuentaEntity quitar = operacionForm.getCuentaByIdcuenta();
            CuentaEntity anadir = operacionForm.getCuentaByIdcuenta2();

            quitar.quitarSaldo(operacionForm.getCantidad());
            cuentaRepository.save(quitar);
            anadir.anadirSaldo(operacionForm.getCantidad());
            cuentaRepository.save(anadir);
                operacionRepository.save(op);
            return "redirect:/cliente/";
        }

    }

    @GetMapping("/cambio")
    public String mostrarCambio(Model model, @RequestParam("id") int idCuenta){
        CuentaEntity cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByIdcuenta(cuenta);
        List<DivisaEntity> divisas = (List<DivisaEntity>) divisaRepository.findAll();
        divisas.remove(cuenta.getDivisaByDivisa());
        model.addAttribute("cuentaCambio",cuenta);
        model.addAttribute("operacionCambio",operacion);
        model.addAttribute("divisas",divisas);
        return "clienteCambio";
    }

    @GetMapping("/valorCambio")
    public String mostrarValor (Model model,  @ModelAttribute("operacionCambio") OperacionEntity operacion){
        model.addAttribute("operacion", operacion);
        if (operacion.getCantidad() > operacion.getCuentaByIdcuenta().getSaldo() || operacion.getCantidad() == 0.00){
            return "clienteError";
        } else {
            return "clienteCambioValor";
        }
    }

    @PostMapping("/doDivisa")
    public String doDivisa (Model model, @ModelAttribute("operacionCambio") OperacionEntity operacionForm){
        TipoOperacionEntity tipo = tipoOperacionRepository.findById(2).orElse(null);
        OperacionEntity op = new OperacionEntity();
        op.setCantidad(operacionForm.getCantidad());
        op.setConcepto(null);
        op.setCuentaByIdcuenta(operacionForm.getCuentaByIdcuenta());
        op.setDivisaByDivisa(operacionForm.getDivisaByDivisa());
        op.setTipoOperacionByTipopperacionid(tipo);
        op.setFecha(new Date());


        ClienteEntity cliente = operacionForm.getCuentaByIdcuenta().getClienteByIdcliente();
        CuentaEntity quitar = operacionForm.getCuentaByIdcuenta();
        CuentaEntity anadir = tieneDivisa(cliente,operacionForm.getDivisaByDivisa());

        quitar.quitarSaldo(operacionForm.getCantidad());
        cuentaRepository.save(quitar);
        Double saldoNuevo = (operacionForm.getCantidad()/operacionForm.getCuentaByIdcuenta().getDivisaByDivisa().getValor() ) * operacionForm.getDivisaByDivisa().getValor();
        if ( anadir != null){
            anadir.anadirSaldo(saldoNuevo);
            cuentaRepository.save(anadir);
        } else {
            CuentaEntity cuentaNueva = new CuentaEntity();
            cuentaNueva.setClienteByIdcliente(cliente);
            cuentaNueva.setSaldo(saldoNuevo);
            cuentaNueva.setDivisaByDivisa(operacionForm.getDivisaByDivisa());
            cuentaNueva.setSospechosa(0);
            cuentaNueva.setActiva(1);
            op.setCuentaByIdcuenta2(cuentaNueva);
            cuentaRepository.save(cuentaNueva);
        }
        operacionRepository.save(op);
            return "redirect:/cliente/";
    }

    private CuentaEntity tieneDivisa (ClienteEntity cliente, DivisaEntity divisa){
        CuentaEntity res = null;
        List<CuentaEntity> cuentas = cliente.getCuentasByIdcliente();
        for (CuentaEntity cuenta : cuentas){
            if (cuenta.getDivisaByDivisa() == divisa){
                    res = cuenta;
            }
        }

        return res;
    }

}
