/*
@author: Luis Ruiz Nuñez(84%) y Pablo Garcia Platero (A partir de la linea 236)(16%).
*/

package com.example.skybank.controller;
import com.example.skybank.dao.*;
import com.example.skybank.dto.*;
import com.example.skybank.entity.ConversacionEntity;
import com.example.skybank.service.*;
import com.example.skybank.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private OperacionService operacionService;

    @Autowired
    private DivisaService divisaService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/")
    public String getClientes(Model model, HttpSession session){
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null){
            return "redirect:/cliente/login";
        } else {
            model.addAttribute("cliente",cliente);
            model.addAttribute("cuentas", clienteService.obtenerCuentasDeCliente(cliente));
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
        Cliente cliente = clienteService.autenticar(user,contra);

        if(cliente == null){
            model.addAttribute("error", "Cliente no encontrado");
            urlTo = "clienteLogin";
        }else{
            if(cliente.getVerificado() == 0){
                model.addAttribute("error", "Cliente no verificado por un Gestor, espere a ser verificado por favor.");
                urlTo = "clienteLogin";
            }else{
                sesion.setAttribute("cliente", cliente);
            }
        }
        return urlTo;
    }


    @GetMapping("/register")
    public String registrarCliente (Model model){
        model.addAttribute("clienteNuevo",new Cliente());
        return "clienteRegister";
    }

    @PostMapping("/crearCliente")
    public String registarCliente (@ModelAttribute("clienteNuevo") Cliente cliente, Model model){
        Cliente c = clienteService.guardarCliente(cliente);
        model.addAttribute("cliente",c);
        return "redirect:/cliente/login";
    }

    @GetMapping("/historial")
    public String verHistorial (Model model, @RequestParam("id") int id){
       Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
       List<Operacion> operaciones = operacionService.obtenerOperacionesCliente(cuenta);
       FiltroOperaciones filtro = new FiltroOperaciones();
       List<TipoOperacion> tipos = operacionService.obtenerTodosTiposOperacion();
       filtro.setIdCuenta(cuenta.getIdcuenta());

       model.addAttribute("operaciones",operaciones);
       model.addAttribute("tipos",tipos);
       model.addAttribute("filtro",filtro);
       model.addAttribute("cuenta",cuenta);

       return "clienteHistorial";
    }

    @PostMapping("/filtrar")
    public String doFiltrar (@ModelAttribute("filtro") FiltroOperaciones filtro,
                             Model model, HttpSession session) {
        return this.procesarFiltrado(filtro,model, session);
    }

    protected String procesarFiltrado (FiltroOperaciones filtro,
                                       Model model, HttpSession session) {

        Cuenta cuenta = cuentaService.obtenerCuentaPorId(filtro.getIdCuenta());
        List<Operacion> operaciones = operacionService.filtrar(filtro);
        List<TipoOperacion> tipos = operacionService.obtenerTodosTiposOperacion();

        model.addAttribute("operaciones",operaciones);
        model.addAttribute("tipos",tipos);
        model.addAttribute("filtro",filtro);
        model.addAttribute("cuenta",cuenta);
        return "clienteHistorial";
    }

    @GetMapping("/editar")
    public String mostrarDatos (Model model, @RequestParam("id") int idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);
        model.addAttribute("cliente",cliente);
        return "clienteEditar";
    }

    @PostMapping("/editar")
    public String doEditar (Model model, @ModelAttribute("cliente") Cliente clienteForm, HttpSession sesion){
        clienteService.editarCliente(clienteForm);
        sesion.setAttribute("cliente",clienteForm);
        return "redirect:/cliente/";
    }

    @GetMapping("/trans")
    public String doTrans (Model model, @RequestParam("id") int idCuenta){
        Cuenta cuentaOrigen = cuentaService.obtenerCuentaPorId(idCuenta);
        List<Cuenta> cuentas = cuentaService.getCuentasByDivisa(cuentaOrigen.getDivisa(), cuentaOrigen.getIdcuenta());

        Operacion operacion = new Operacion();
        operacion.setCuentaOrigen(cuentaOrigen);
        operacion.setDivisa(cuentaOrigen.getDivisa());

        model.addAttribute("cuentaOrigen",cuentaOrigen);
        model.addAttribute("cuentas",cuentas);
        model.addAttribute("operacion",operacion);
        return "clienteTransf";
    }

    @PostMapping("/doTransf")
    public String realizarTrans (Model model, @ModelAttribute("operacion") Operacion operacionForm){
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(operacionForm.getCuentaOrigen().getIdcuenta());
        Double saldo = cuenta.getSaldo();

        if (operacionForm.getCantidad() > saldo || operacionForm.getCantidad() <= 0.00){
            model.addAttribute("tipo", 1);
            model.addAttribute("cuenta",cuenta);
            return "clienteError";
        } else {
            operacionService.realizarTransferenciaCliente(operacionForm);
            return "redirect:/cliente/";
        }
    }

    @GetMapping("/cambio")
    public String mostrarCambio(Model model, @RequestParam("id") int idCuenta){
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(idCuenta);
        Operacion operacion = new Operacion();
        operacion.setCuentaOrigen(cuenta);
        List<Divisa> divisas = divisaService.obtenerTodasLasDivisasMenos(cuenta.getDivisa());

        model.addAttribute("cuenta",cuenta);
        model.addAttribute("operacionCambio",operacion);
        model.addAttribute("divisas",divisas);
        return "clienteCambio";
    }

    @GetMapping("/valorCambio")
    public String mostrarValor (Model model,  @ModelAttribute("operacionCambio") Operacion operacionForm) {

        Double saldo = cuentaService.obtenerCuentaPorId(operacionForm.getCuentaOrigen().getIdcuenta()).getSaldo();
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(operacionForm.getCuentaOrigen().getIdcuenta());
        model.addAttribute("cuenta",cuenta);

        if (operacionForm.getCantidad() > saldo || operacionForm.getCantidad() <= 0.00){
            model.addAttribute("tipo",2);
            return "clienteError";
        } else {

        model.addAttribute("operacion", operacionForm);
        Divisa divisaOp = divisaService.obtenerDivisa(operacionForm.getDivisa().getIddivisa());
        Divisa divisaCu = divisaService.obtenerDivisa(cuenta.getDivisa().getIddivisa());

        model.addAttribute("divisa",divisaOp);
        model.addAttribute("divisaC", divisaCu);

        return "clienteCambioValor";
        }
    }

    @PostMapping("/doDivisa")
    public String doDivisa (Model model, @ModelAttribute("operacionCambio") Operacion operacionForm){
        operacionService.realizarCambioDivisa(operacionForm);
        return "redirect:/cliente/";
    }

    @GetMapping("/solicitar")
    public String soliticar (Model model, @RequestParam("id") int idcliente){
        clienteService.solicitar(idcliente);
        Cliente cliente = clienteService.getClienteById(idcliente);
        model.addAttribute("cliente", cliente);
        model.addAttribute("cuentas", clienteService.obtenerCuentasDeCliente(cliente));
        return "cliente";
    }



    //Parte Asistente (Pablo Garcia Platero)

    @GetMapping("/chatsCliente")
    public String mostrarChat(HttpSession session, Model model){
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        List<Chat> chats = this.chatService.listaChatsDeCliente(cliente.getIdcliente());
        model.addAttribute("chats",chats);
        return "chatsCliente";
    }

    @GetMapping("/chat")
    public String mostrarChatPrivado(Model model, @RequestParam("idconversacion") Integer idChat){
        Chat chat = this.chatService.buscarChat(idChat);
        List<Mensaje> mensajesEntities = this.mensajeService.listMensajesPorIdChat(idChat);
        model.addAttribute("chat",chat);
        model.addAttribute("mensajes",mensajesEntities);
        return "chatCliente";
    }

    @PostMapping("/crearNuevoMensaje")
    public String agregarMensaje(HttpSession session, @RequestParam("mensaje")String mensaje,@RequestParam("idconversacion") Integer idChat){
        if(mensaje.equals("")|| mensaje == null){

        }else{
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            mensaje = cliente.getNombre() + " " + cliente.getApellido1() + ": " + mensaje;
            chatService.agregarMensaje(idChat,mensaje);
        }
        return "redirect:/cliente/chat?idconversacion=" + idChat;
    }

    @GetMapping("/cerrar")
    public String cerrarConversacion(Model model, @RequestParam("idconversacion") Integer idChat){
        ConversacionEntity conversacion = chatRepository.getById(idChat);
        conversacion.setCerrada((byte) 1);
        chatRepository.save(conversacion);
        return "redirect:/cliente/chatsCliente";
    }

    @GetMapping("/nuevaConversacion")
    public String nuevaConversacion(@RequestParam("idcliente") Integer idcliente){
        chatService.agregarConversacion(idcliente);

        return "redirect:/cliente/chatsCliente";
    }
}


