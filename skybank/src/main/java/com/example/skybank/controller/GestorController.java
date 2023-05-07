package com.example.skybank.controller;

/*
 * @author Rafael Ceballos Martinez
 */

import com.example.skybank.dto.*;
import com.example.skybank.dto.Gestor;
import com.example.skybank.service.*;
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
    private GestorService gestorService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private OperacionService operacionService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private SocioService socioService;


    @GetMapping("/")
    public String getListadoCuentas(HttpSession session, Model model){
        com.example.skybank.dto.Gestor gestor = (com.example.skybank.dto.Gestor) session.getAttribute("gestor");

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

        com.example.skybank.dto.Gestor gestor = gestorService.autenticar(user,pass);
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
        Gestor gestor = (Gestor) session.getAttribute("gestor");

        if (gestor == null){
            return "redirect:/gestor/login";
        }else{
            List<Cliente> solicitadas = clienteService.getPendientesVerificacion();
            model.addAttribute("solicitadas",solicitadas);

            List<Empresa> solicitadasE = empresaService.getPendientesVerificacion();
            model.addAttribute("solicitadasE", solicitadasE);

            List<Cliente> solicitudReativacion = clienteService.getSolicitudesReactivacion();
            model.addAttribute("solicitudesReactivar", solicitudReativacion);

            List<Socio> solicitudDesbloqueo = socioService.getSolicitudesDebloqueo();
            model.addAttribute("socioDesbloqueo", solicitudDesbloqueo);
        }

        return "solicitudes";
    }

    @GetMapping("/desbloquearSocio")
    public String desbloquarSocio(Model model, @RequestParam("postId") int idSocio){
        Socio socio = socioService.obtenerSocio(idSocio);

        socio.setBloqueado(0);
        socio.setSolicituddesbloqueo(0);
        socioService.editarSocio(socio, socio.getEmpresa().getIdempresa());

        return "redirect:/gestor/solicitudes";
    }

    @GetMapping("/reactivarCliente")
    public String reactivarCliente(Model model, @RequestParam("postId") int idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);

        cliente.setBloqueado(0);
        cliente.setSolicitudactivacion(0);
        clienteService.editarCliente(cliente);

        return "redirect:/gestor/solicitudes";
    }

    @GetMapping("/aceptarCliente")
    public String aceptarCliente(Model model, @RequestParam("postId") int idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);

        cliente.setVerificado(1);
        clienteService.editarCliente(cliente);
        return "redirect:/gestor/solicitudes";
    }

    @GetMapping("/rechazarCliente")
    public String rechazarCliente(Model model, @RequestParam("postId") int idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);

        clienteService.eliminarCliente(cliente);
        return "redirect:/gestor/solicitudes";
    }

    @GetMapping("/aceptarEmpresa")
    public String aceptarEmpresa(Model model, @RequestParam("postId") int idEmpresa){
        Empresa empresa = empresaService.getEmpresaById(idEmpresa);

        empresa.setVerificado(1);
        empresaService.editarEmpresa(empresa);
        return "redirect:/gestor/solicitudes";
    }

    @GetMapping("/rechazarEmpresa")
    public String rechazarEmpresa(Model model, @RequestParam("postId") int idEmpresa){
        Empresa empresa = empresaService.getEmpresaById(idEmpresa);

        empresaService.eliminarEmpresa(empresa);
        return "redirect:/gestor/solicitudes";
    }

    @PostMapping("/")
    public String doFiltrarClientesYEmpresas(@ModelAttribute("filtro") FiltroListadoClientes filtro, HttpSession session, Model model){
        return filtrarClientesYEmpresas(filtro,(com.example.skybank.dto.Gestor) session.getAttribute("gestor"),model);
    }

    private String filtrarClientesYEmpresas(FiltroListadoClientes filtro, com.example.skybank.dto.Gestor gestor, Model model){
        model.addAttribute("gestor", gestor);

        List<Cliente> listaClientes = new ArrayList<>();
        List<Empresa> listaEmpresas = new ArrayList<>();

        if(filtro == null || (filtro != null && filtro.getTexto().isEmpty() && filtro.isClientes() && filtro.isEmpresas())){
            FiltroListadoClientes f = new FiltroListadoClientes();
            f.setEmpresas(true);
            f.setClientes(true);

            model.addAttribute("filtro",f);


            listaClientes = clienteService.obtenerTodosLosClientes();
            listaEmpresas = empresaService.obtenerTodasLasEmpresas();

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
        Gestor gestor = (Gestor) session.getAttribute("gestor");

        if (gestor == null){
            return "redirect:/gestor/login";
        }else{
            boolean encontrado = false;
            Date fechaActual = new Date();
            List<Operacion> operaciones = operacionService.obtenerOperacionesRecientes(fechaActual);

            List<Cliente> clientesTotales = clienteService.obtenerTodosLosClientes();
            List<Empresa> empresasTotales = empresaService.obtenerTodasLasEmpresas();

            List<Cliente> clientesSinUso = new ArrayList<>();
            List<Empresa> empresasSinUso = new ArrayList<>();

            for(Cliente c : clientesTotales){
                    for(Operacion o : operaciones){
                        if((o.getCuentaOrigen() != null) && (cuentaService.getCuentasCliente(c)).contains(o.getCuentaOrigen())){
                            encontrado = true;
                            break;
                        }
                    }
                    if(!encontrado){
                        clientesSinUso.add(c);
                    }
                    encontrado = false;
                }

            for(Empresa e : empresasTotales){

                    for(Operacion o : operaciones){
                        if((o.getCuentaOrigen() != null) && (cuentaService.getCuentasEmpresa(e).contains(o.getCuentaOrigen()))){
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
        Cliente cliente = clienteService.getClienteById(idCliente);

        cliente.setBloqueado(1);
        clienteService.editarCliente(cliente);
        return "redirect:/gestor/cuentasSinUso";
    }

    @GetMapping("/bloquearEmpresa")
    public String bloquearEmpresa(Model model, @RequestParam("postId") int idEmpresa) {
        Empresa empresa =empresaService.getEmpresaById(idEmpresa);

        empresa.setBloqueada(1);
        empresaService.editarEmpresa(empresa);
        return "redirect:/gestor/cuentasSinUso";
    }
    @GetMapping("/desbloquearCliente")
    public String desbloquearCliente(Model model, @RequestParam("postId") int idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);

        cliente.setBloqueado(0);
        clienteService.editarCliente(cliente);
        return "redirect:/gestor/cuentasSinUso";
    }
    @GetMapping("/desbloquearEmpresa")
    public String desbloquearEmpresa(Model model, @RequestParam("postId") int idEmpresa){
        Empresa empresa =empresaService.getEmpresaById(idEmpresa);

        empresa.setBloqueada(0);
        empresaService.editarEmpresa(empresa);
        return "redirect:/gestor/cuentasSinUso";
    }

    @GetMapping("/sospechas")
    public String mostrarSospechas(Model model, HttpSession session){
        List<Operacion> opSospechosas = operacionService.obtenerOperacionesSospechosas();
        List<Cliente> clientesSospechosos = new ArrayList<>();
        List<Empresa> empresasSospechosas = new ArrayList<>();

        for(Operacion o : opSospechosas){
            if(empresaService.getEmpresaByCuentaId(o.getCuentaOrigen().getIdcuenta()) != null){
                if(!empresasSospechosas.contains(empresaService.getEmpresaByCuentaId(o.getCuentaOrigen().getIdcuenta()))){
                    empresasSospechosas.add(empresaService.getEmpresaByCuentaId(o.getCuentaOrigen().getIdcuenta()));
                }
            }
            if(clienteService.getClienteByCuentaId(o.getCuentaOrigen().getIdcuenta()) != null){
                if(!clientesSospechosos.contains(clienteService.getClienteByCuentaId(o.getCuentaOrigen().getIdcuenta()))){
                    clientesSospechosos.add(clienteService.getClienteByCuentaId(o.getCuentaOrigen().getIdcuenta()));
                }
            }
        }


        model.addAttribute("clientesSospechosos", clientesSospechosos);
        model.addAttribute("empresasSospechosas", empresasSospechosas);

        return "listaSospechosas";
    }

    @GetMapping("/bloquearCliente2")
    public String bloquearCliente2(Model model, @RequestParam("postId") int idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);

        cliente.setBloqueado(1);
        clienteService.editarCliente(cliente);
        return "redirect:/gestor/sospechas";
    }

    @GetMapping("/bloquearEmpresa2")
    public String bloquearEmpresa2(Model model, @RequestParam("postId") int idEmpresa){
        Empresa empresa =empresaService.getEmpresaById(idEmpresa);

        empresa.setBloqueada(1);
        empresaService.editarEmpresa(empresa);
        return "redirect:/gestor/sospechas";
    }

    @GetMapping("/desbloquearCliente2")
    public String desbloquearCliente2(Model model, @RequestParam("postId") int idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);

        cliente.setBloqueado(0);
        clienteService.editarCliente(cliente);
        return "redirect:/gestor/sospechas";
    }

    @GetMapping("/desbloquearEmpresa2")
    public String desbloquearEmpresa2(Model model, @RequestParam("postId") int idEmpresa){
        Empresa empresa =empresaService.getEmpresaById(idEmpresa);

        empresa.setBloqueada(0);
        empresaService.editarEmpresa(empresa);
        return "redirect:/gestor/sospechas";
    }


    @GetMapping("/gestionarCliente")
    public String verCliente (Model model, @RequestParam("postId") int idCliente, HttpSession session){


        Cliente cliente = clienteService.getClienteById(idCliente);;

        model.addAttribute("cliente",cliente);

        return "gestorCliente";
    }

    @GetMapping("/gestionarEmpresa")
    public String verEmpresa (Model model, @RequestParam("postId") int idEmpresa){

        Empresa empresa =empresaService.getEmpresaById(idEmpresa);

        model.addAttribute("empresa",empresa);

        return "gestorEmpresa";
    }

    @GetMapping("/historial")
    public String verHistorial (Model model, @RequestParam("postId") int id, @RequestParam("tipo") int tipo, HttpSession session){
        com.example.skybank.dto.Gestor gestor = (com.example.skybank.dto.Gestor) session.getAttribute("gestor");
        if (gestor == null){
            return "redirect:/gestor/login";
        }else{
            List<Cuenta> cuentas;
            FiltroOperaciones filtro = new FiltroOperaciones();
            Cliente cliente = null;
            Empresa empresa = null;
            if(tipo==0){
                cliente = clienteService.getClienteById(id);
                cuentas = cuentaService.getCuentasCliente(cliente);
            }else{
                empresa = empresaService.getEmpresaById(id);
                cuentas = cuentaService.getCuentasEmpresa(empresa);
            }

            List<Operacion> operaciones = new ArrayList<>();
            List<Operacion> opAux = new ArrayList<>();

            if(!cuentas.isEmpty()){
                for(Cuenta c : cuentas){
                    opAux = operacionService.obtenerOperacionesCliente(c);
                    if(opAux!=null){
                        for(Operacion o : opAux){
                            if(o.getCantidad() > 0){
                                operaciones.add(o);
                            }
                        }
                    }
                }

                filtro.setIdCuenta(cuentas.get(0).getIdcuenta());
            }

            model.addAttribute("filtro",filtro);
            model.addAttribute("operaciones",operaciones);
            model.addAttribute("cliente",cliente);
            model.addAttribute("empresa",empresa);
            return "historialCliente";
        }
    }
    @PostMapping("/filtrar")
    public String doFiltrar (@ModelAttribute("filtro") FiltroOperaciones filtro,
                             Model model, HttpSession session) {
        return this.procesarFiltrado(filtro,model, session);
    }

    protected String procesarFiltrado (FiltroOperaciones filtro,
                                       Model model, HttpSession session){

        Cuenta cuenta = cuentaService.obtenerCuentaPorId(filtro.getIdCuenta());
        List<Operacion> operaciones = operacionService.filtrar(filtro);
        List<TipoOperacion> tipos = operacionService.obtenerTodosTiposOperacion();
        Cliente c = clienteService.getClienteByCuentaId(cuenta.getIdcuenta());


        model.addAttribute("operaciones",operaciones);
        model.addAttribute("tipos",tipos);
        model.addAttribute("filtro",filtro);
        model.addAttribute("cliente",c);
        model.addAttribute("cuenta",cuenta);
        return "historialCliente";
    }

    @GetMapping("/historialEmpresa")
    public String verHistorialEmpresa (Model model, @RequestParam("postId") int id, @RequestParam("tipo") int tipo, HttpSession session){
        com.example.skybank.dto.Gestor gestor = (com.example.skybank.dto.Gestor) session.getAttribute("gestor");
        if (gestor == null){
            return "redirect:/gestor/login";
        }else{
            List<Cuenta> cuentas;
            FiltroOperaciones filtro = new FiltroOperaciones();
            Cliente cliente = null;
            Empresa empresa = null;
            if(tipo==0){
                cliente = clienteService.getClienteById(id);
                cuentas = cuentaService.getCuentasCliente(cliente);
            }else{
                empresa = empresaService.getEmpresaById(id);
                cuentas = cuentaService.getCuentasEmpresa(empresa);
            }

            List<Operacion> operaciones = new ArrayList<>();
            List<Operacion> opAux = new ArrayList<>();

            if(!cuentas.isEmpty()){
                for(Cuenta c : cuentas){
                    opAux = operacionService.obtenerOperacionesEmpresa(c);
                    if(opAux!=null){
                        for(Operacion o : opAux){
                            if(o.getCantidad() > 0){
                                operaciones.add(o);
                            }
                        }
                    }
                }

                filtro.setIdCuenta(cuentas.get(0).getIdcuenta());
            }

            model.addAttribute("filtro",filtro);
            model.addAttribute("operaciones",operaciones);
            model.addAttribute("empresa",empresa);
            model.addAttribute("empresa",empresa);
            return "historialEmpresa";
        }
    }
    @PostMapping("/filtrar2")
    public String doFiltrar2 (@ModelAttribute("filtro") FiltroOperaciones filtro,
                             Model model, HttpSession session) {
        return this.procesarFiltrado2(filtro,model, session);
    }

    protected String procesarFiltrado2 (FiltroOperaciones filtro,
                                       Model model, HttpSession session){

        Cuenta cuenta = cuentaService.obtenerCuentaPorId(filtro.getIdCuenta());
        List<Operacion> aux = operacionService.filtrar(filtro);
        List<Operacion> operaciones = new ArrayList<>();
        for(Operacion o : aux){
            if(o.getCantidad() > 0){
                operaciones.add(o);
            }
        }
        List<TipoOperacion> tipos = operacionService.obtenerTodosTiposOperacion();
        Empresa e = empresaService.getEmpresaByCuentaId(cuenta.getIdcuenta());


        model.addAttribute("operaciones",operaciones);
        model.addAttribute("tipos",tipos);
        model.addAttribute("filtro",filtro);
        model.addAttribute("empresa",e);
        model.addAttribute("cuenta",cuenta);
        return "historialEmpresa";
    }
}