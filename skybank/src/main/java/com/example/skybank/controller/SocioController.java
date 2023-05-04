/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.controller;

import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.dto.Autorizado;
import com.example.skybank.dto.Empresa;
import com.example.skybank.dto.Socio;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
import com.example.skybank.service.AutorizadoService;
import com.example.skybank.service.EmpresaService;
import com.example.skybank.service.SocioService;
import com.example.skybank.ui.FiltroSociosAutorizados;
import com.example.skybank.ui.TipoPersonaEmpresa;
import com.example.skybank.ui.socioOAutorizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empresa/socios/")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @Autowired
    private AutorizadoService autorizadoService;

    @Autowired
    private EmpresaService empresaService;


    @GetMapping("/")
    public String mostrarSociosYAutorizados(Model model, HttpSession session){
        Empresa empresa = (Empresa) session.getAttribute("empresa");
        if(empresa == null){
            return "redirect:/empresa/login";
        }else{
            return filtrarSociosYAutorizados(null,empresa,model);
        }

    }

    @PostMapping("/")
    public String doFiltrarSociosYAutorizados(@ModelAttribute("filtro") FiltroSociosAutorizados filtro, HttpSession session, Model model){
        return filtrarSociosYAutorizados(filtro,(Empresa) session.getAttribute("empresa"),model);
    }

    private String filtrarSociosYAutorizados(FiltroSociosAutorizados filtro, Empresa empresa, Model model){
        model.addAttribute("empresa", empresa);
        model.addAttribute("NuevoSocioOAutorizado", new socioOAutorizado(null));

        List<Socio> socios = new ArrayList<>();
        List<Autorizado> autorizados = new ArrayList<>();

        if(filtro == null || (filtro != null && filtro.getTexto().isEmpty() && filtro.isSocios() && filtro.isAutorizados())){
            FiltroSociosAutorizados f = new FiltroSociosAutorizados();
            f.setAutorizados(true);
            f.setSocios(true);

            model.addAttribute("filtro",f);


            socios = socioService.getAllSociosOfEmpresa(empresa);
            autorizados = autorizadoService.getAllAutorizadosOfEmpresa(empresa);

        }else if(filtro != null){
            System.out.println(filtro.getTexto() + " a: " + filtro.isAutorizados() + " s: " + filtro.isSocios());

            model.addAttribute("filtro",filtro);

            if(filtro.isAutorizados() && filtro.isSocios()){
                socios = socioService.getAllSociosOfEmpresaFiltered(empresa,filtro.getTexto());
                autorizados = autorizadoService.getAllAutorizadosOfEmpresaFiltered(empresa,filtro.getTexto());
            }else if(filtro.isAutorizados() && !filtro.isSocios()){
                autorizados = autorizadoService.getAllAutorizadosOfEmpresaFiltered(empresa,filtro.getTexto());
            }else if(!filtro.isAutorizados() && filtro.isSocios()){
                socios = socioService.getAllSociosOfEmpresaFiltered(empresa,filtro.getTexto());
            }
        }

        model.addAttribute("socios", socios);
        model.addAttribute("autorizados", autorizados);
        return "sociosYAutorizados";

    }

    @PostMapping("/crearSocioEmpresa")
    public String crearPrimerSocioEmpresa(@ModelAttribute("socio") Socio socio, @RequestParam("id") Integer idEmpresa){
        socioService.crearSocioEmpresa(socio,idEmpresa);

        return "redirect:/empresa/login";
    }

    @PostMapping("/addSocioOrAutorizado")
    public String addSocioOrAutorizado(@ModelAttribute("NuevoSocioOAutorizado") socioOAutorizado persona, @RequestParam("id") Integer idEmpresa,HttpSession session) {
        socioService.addSocioOrAutorizado(persona,idEmpresa);

        session.setAttribute("empresa", empresaService.obtenerEmpresaPorIdEmpresa(idEmpresa));
        return "redirect:/empresa/socios/";
    }


    @GetMapping("/bloquear")
    public String bloquearSocio(@RequestParam("id") Integer idSocio,@RequestParam("e") Integer idEmpresa, HttpSession session){
        socioService.bloquearSocio(idSocio);

        session.setAttribute("empresa", empresaService.obtenerEmpresaPorIdEmpresa(idEmpresa));
        return "redirect:/empresa/socios/";
    }

    @GetMapping("/borrar")
    public String borrarSocio(@RequestParam("id") Integer idSocio,@RequestParam("e") Integer idEmpresa, HttpSession sesion){

        socioService.borrarSocio(idSocio);

        sesion.setAttribute("empresa",empresaService.obtenerEmpresaPorIdEmpresa(idEmpresa));
        return "redirect:/empresa/socios/";
    }

    @GetMapping("/login")
    public String loginEmpresa(){
        return "loginSocioAutorizado";
    }

    @PostMapping("/login")
    public String String(@RequestParam("nif") String nif, @RequestParam("password") String password
            ,HttpSession sesion,Model modelo){

        String urlTo = "redirect:/empresa/";

        Autorizado autorizado = autorizadoService.autenticarAutorizado(nif, password);

        Object cuenta = autorizado != null ? autorizado : socioService.autenticarSocio(nif,password);

        if(cuenta == null) {
            modelo.addAttribute("error", "Usuario no encontrado");
            urlTo = "loginSocioAutorizado";

        }else{
            Empresa empresa;
            String tipoCuenta = "";
            try{
                empresa = autorizadoService.obtenerEmpresaAutorizado((Autorizado) cuenta);
                tipoCuenta = "Autorizado";
            }catch (Exception e){
                empresa = socioService.obtenerEmpresaSocio((Socio) cuenta);
                tipoCuenta = "Socio";

            }

            if((tipoCuenta.equals("Autorizado") && ((Autorizado) cuenta).getBloqueado() == 1) || (tipoCuenta.equals("Socio") && ((Socio) cuenta).getBloqueado() == 1)) {
                modelo.addAttribute("error", String.format("Esta cuenta ha sido bloqueada, solicite el desbloqueo entrando en el siguiente enlace: <br><a href='/empresa/socios/solicitarDesbloqueo?%s'>Solicitar desbloqueo.</a>",cuenta.getClass() == Autorizado.class ? "id=" + ((Autorizado) cuenta).getId()  + "&tipo=Autorizado" : "id=" + ((Socio) cuenta).getId() + "&tipo=Socio"));
                urlTo = "loginSocioAutorizado";
            }else{
                if(empresa.getVerificado() == 1){
                    sesion.setAttribute("empresa",empresa);
                    sesion.setAttribute("cuenta", cuenta);
                    sesion.setAttribute("tipoCuenta",tipoCuenta);

                }else{
                    modelo.addAttribute("error", "Empresa no verificada por un Gestor, espere a que sea verificada por favor.");
                    urlTo = "loginSocioAutorizado";
                }
            }

        }

        return urlTo;

    }


    @GetMapping("/{id}")
    public String mostrarDatosSocio(@PathVariable("id") Integer idSocio, Model model){
        model.addAttribute("socio", socioService.obtenerSocio(idSocio));

        return "datosSocio";
    }

    @PostMapping("/edit")
    public String editarSocio(@ModelAttribute("socio") Socio socio, @RequestParam("eId") Integer empresaId, HttpSession session){

        socioService.editarSocio(socio,empresaId);

        session.setAttribute("empresa", empresaService.obtenerEmpresaPorIdEmpresa(empresaId));
        session.setAttribute("cuenta",socio);
        session.setAttribute("tipoCuenta","Socio");

        return "redirect:/empresa/autorizados/" + socio.getId();
    }

    @GetMapping("/solicitarDesbloqueo")
    public String solicitarDesbloqueo(@RequestParam("id") Integer idCuenta,@RequestParam("tipo") String tipoCuenta){

        if(tipoCuenta.equals("Socio")){
            socioService.solicitarDesbloqueo(idCuenta);
        }else if(tipoCuenta.equals("Autorizado")){
            autorizadoService.solicitarDesbloqueo(idCuenta);
        }
        return "solicitarDesbloqueo";

    }

}
