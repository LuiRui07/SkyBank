package com.example.skybank.controller;

/*
 * @author Rafael Ceballos
 */

import com.example.skybank.dao.*;
import com.example.skybank.dto.Cuenta;
import com.example.skybank.dto.Operacion;
import com.example.skybank.dto.TipoOperacion;
import com.example.skybank.entity.*;
import com.example.skybank.service.GestorService;
import com.example.skybank.ui.FiltroListadoClientes;
import com.example.skybank.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorRepository gestorRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TipoOperacionRepository tipoOperacionRepository;

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

            List<ClienteEntity> solicitudReativacion = clienteRepository.getSolicitudesReactivacion();
            model.addAttribute("solicitudesReactivar", solicitudReativacion);

            List<SocioEntity> solicitudDesbloqueo = socioRepository.getSolicitudDesbloqueo();
            model.addAttribute("socioDesbloqueo", solicitudDesbloqueo);
        }

        return "solicitudes";
    }

    @GetMapping("/desbloquearSocio")
    public String desbloquarSocio(Model model, @RequestParam("postId") int idSocio){
        SocioEntity socio = socioRepository.findById(idSocio).orElse(null);

        socio.setBloqueado(0);
        socio.setSolicituddesbloqueo(0);
        socioRepository.save(socio);

        return "redirect:/gestor/solicitudes";
    }

    @GetMapping("/reactivarCliente")
    public String reactivarCliente(Model model, @RequestParam("postId") int idCliente){
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        cliente.setBloqueado(0);
        cliente.setSolicitudactivacion(0);
        clienteRepository.save(cliente);

        return "redirect:/gestor/solicitudes";
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

    @GetMapping("/cuentasSinUso")
    public String getCuentasSinUso(Model model, HttpSession session){
        GestorEntity gestor = (GestorEntity) session.getAttribute("gestor");

        if (gestor == null){
            return "redirect:/gestor/login";
        }else{
            boolean encontrado = false;
            Date fechaActual = new Date();
            List<OperacionEntity> operaciones = operacionRepository.obtenerOperacionesRecientes(fechaActual);

            List<ClienteEntity> clientesTotales = clienteRepository.findAll();
            List<EmpresaEntity> empresasTotales = empresaRepository.findAll();

            List<ClienteEntity> clientesSinUso = new ArrayList<>();
            List<EmpresaEntity> empresasSinUso = new ArrayList<>();

            for(ClienteEntity c : clientesTotales){

                    for(OperacionEntity o : operaciones){
                        if((o.getCuentaByIdcuenta().getClienteByIdcliente() != null) && (o.getCuentaByIdcuenta().getClienteByIdcliente().getIdcliente() == (c.getIdcliente()))){
                            encontrado = true;
                            break;
                        }
                    }
                    if(!encontrado){
                        clientesSinUso.add(c);
                    }
                    encontrado = false;
                }

            for(EmpresaEntity e : empresasTotales){

                    for(OperacionEntity o : operaciones){
                        if((o.getCuentaByIdcuenta().getEmpresaByIdempresa() != null) && (o.getCuentaByIdcuenta().getEmpresaByIdempresa().getIdempresa() == (e.getIdempresa()))){
                            encontrado = true;
                            break;
                        }
                    }
                    if(!encontrado){
                        empresasSinUso.add(e);
                    }
                    encontrado = false;
            }
                model.addAttribute("listaClientesObsoletos", clientesSinUso);
                model.addAttribute("listaEmpresasObsoletas", empresasSinUso);

            return "listadoCuentasObsoletas";
        }

    }

    @GetMapping("/bloquearCliente")
    public String bloquearCliente(Model model, @RequestParam("postId") int idCliente){
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        cliente.setBloqueado(1);
        clienteRepository.save(cliente);
        return "redirect:/gestor/cuentasSinUso";
    }

    @GetMapping("/bloquearEmpresa")
    public String bloquearEmpresa(Model model, @RequestParam("postId") int idEmpresa) {
        EmpresaEntity empresa = empresaRepository.findById(idEmpresa).orElse(null);

        empresa.setBloqueada(1);
        empresaRepository.save(empresa);
        return "redirect:/gestor/cuentasSinUso";
    }
    @GetMapping("/desbloquearCliente")
    public String desbloquearCliente(Model model, @RequestParam("postId") int idCliente){
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        cliente.setBloqueado(0);
        clienteRepository.save(cliente);
        return "redirect:/gestor/cuentasSinUso";
    }
    @GetMapping("/desbloquearEmpresa")
    public String desbloquearEmpresa(Model model, @RequestParam("postId") int idEmpresa){
        EmpresaEntity empresa = empresaRepository.findById(idEmpresa).orElse(null);

        empresa.setBloqueada(0);
        empresaRepository.save(empresa);
        return "redirect:/gestor/cuentasSinUso";
    }

    @GetMapping("/sospechas")
    public String mostrarSospechas(Model model, HttpSession session){
        List<OperacionEntity> opSospechosas = operacionRepository.obtenerOperacionesSospechosas();
        List<ClienteEntity> clientesSospechosos = new ArrayList<>();
        List<EmpresaEntity> empresasSospechosas = new ArrayList<>();

        for(OperacionEntity o : opSospechosas){
                if(o.getCuentaByIdcuenta().getClienteByIdcliente() != null && !clientesSospechosos.contains(o.getCuentaByIdcuenta().getClienteByIdcliente())){
                    clientesSospechosos.add(o.getCuentaByIdcuenta().getClienteByIdcliente());
                }else if(o.getCuentaByIdcuenta().getEmpresaByIdempresa() != null && !empresasSospechosas.contains(o.getCuentaByIdcuenta().getEmpresaByIdempresa())){
                    empresasSospechosas.add(o.getCuentaByIdcuenta().getEmpresaByIdempresa());
                }
        }

        model.addAttribute("clientesSospechosos", clientesSospechosos);
        model.addAttribute("empresasSospechosas", empresasSospechosas);

        return "listaSospechosas";
    }

    @GetMapping("/bloquearCliente2")
    public String bloquearCliente2(Model model, @RequestParam("postId") int idCliente){
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        cliente.setBloqueado(1);
        clienteRepository.save(cliente);
        return "redirect:/gestor/sospechas";
    }

    @GetMapping("/bloquearEmpresa2")
    public String bloquearEmpresa2(Model model, @RequestParam("postId") int idEmpresa){
        EmpresaEntity empresa = empresaRepository.findById(idEmpresa).orElse(null);

        empresa.setBloqueada(1);
        empresaRepository.save(empresa);
        return "redirect:/gestor/sospechas";
    }

    @GetMapping("/desbloquearCliente2")
    public String desbloquearCliente2(Model model, @RequestParam("postId") int idCliente){
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        cliente.setBloqueado(0);
        clienteRepository.save(cliente);
        return "redirect:/gestor/sospechas";
    }

    @GetMapping("/desbloquearEmpresa2")
    public String desbloquearEmpresa2(Model model, @RequestParam("postId") int idEmpresa){
        EmpresaEntity empresa = empresaRepository.findById(idEmpresa).orElse(null);

        empresa.setBloqueada(0);
        empresaRepository.save(empresa);
        return "redirect:/gestor/sospechas";
    }


    @GetMapping("/gestionarCliente")
    public String verCliente (Model model, @RequestParam("postId") int idCliente){

        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);

        model.addAttribute("cliente",cliente);

        return "gestorCliente";
    }

    @GetMapping("/gestionarEmpresa")
    public String verEmpresa (Model model, @RequestParam("postId") int idEmpresa){

        EmpresaEntity empresa = empresaRepository.findById(idEmpresa).orElse(null);

        model.addAttribute("empresa",empresa);

        return "gestorEmpresa";
    }

    @GetMapping("/historial")
    public String verHistorial (Model model, @RequestParam("postId") int id, @RequestParam("tipo") int tipo){

        List<CuentaEntity> cuentas = new ArrayList<>();
        ClienteEntity cliente = null;
        EmpresaEntity empresa = null;
        if(tipo==0){
            cliente = clienteRepository.findById(id).orElse(null);
            cuentas = cuentaRepository.findByCliente(id);
        }else{
            empresa = empresaRepository.findById(id).orElse(null);
            cuentas = cuentaRepository.findByEmpresa(id);
        }


        List<OperacionEntity> operaciones = new ArrayList<>();
        List<OperacionEntity> opAux = new ArrayList<>();

        if(cuentas!=null){
            for(CuentaEntity c : cuentas){
                opAux = c.getOperacionsByIdcuenta();
                if(opAux!=null){
                    for(OperacionEntity o : opAux){
                        if(o.getCantidad() > 0){
                            operaciones.add(o);
                        }
                    }
                }
            }
        }

        model.addAttribute("operaciones",operaciones);
        model.addAttribute("cliente",cliente);
        model.addAttribute("empresa",empresa);
        return "historialCliente";
    }

    @PostMapping("/")
    public String doFiltrarHistorial(@ModelAttribute("filtro") FiltroListadoClientes filtro, HttpSession session, Model model){
        return filtrarHistorial(filtro,(GestorEntity) session.getAttribute("gestor"),model);
    }

    private String filtrarHistorial(FiltroOperaciones filtro, GestorEntity gestor, Model model){
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