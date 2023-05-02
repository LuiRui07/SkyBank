/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.controller;

import com.example.skybank.dao.AutorizadoRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.SocioRepository;
import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.SocioEntity;
import com.example.skybank.service.AutorizadoService;
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
    private SocioRepository socioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AutorizadoRepository autorizadoRepository;


    @GetMapping("/")
    public String mostrarSociosYAutorizados(Model model, HttpSession session){
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        if(empresa == null){
            return "redirect:/empresa/login";
        }else{
            return filtrarSociosYAutorizados(null,empresa,model);
        }

    }

    @PostMapping("/")
    public String doFiltrarSociosYAutorizados(@ModelAttribute("filtro") FiltroSociosAutorizados filtro, HttpSession session, Model model){
        return filtrarSociosYAutorizados(filtro,(EmpresaEntity) session.getAttribute("empresa"),model);
    }

    private String filtrarSociosYAutorizados(FiltroSociosAutorizados filtro, EmpresaEntity empresa, Model model){
        model.addAttribute("empresa", empresa);
        model.addAttribute("NuevoSocioOAutorizado", new socioOAutorizado(null));

        List<SocioEntity> socios = new ArrayList<>();
        List<AutorizadoEntity> autorizados = new ArrayList<>();

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
    public String crearPrimerSocioEmpresa(@ModelAttribute("socio") SocioEntity socio, @RequestParam("id") Integer idEmpresa){
       EmpresaEntity empresa = empresaRepository.getById(idEmpresa);

       System.out.println(empresa.getNombre() + " " + idEmpresa.toString());
       socio.setEmpresaByIdempresa(empresa);
       socioRepository.save(socio);

       System.out.println(empresa.getSociosByIdempresa());
       //empresa.getSociosByIdEmpresa().add(socio);
       //empresaRepository.save(empresa);
       return "redirect:/empresa/login";
    }

    @PostMapping("/addSocioOrAutorizado")
    public String addSocioOrAutorizado(@ModelAttribute("NuevoSocioOAutorizado") socioOAutorizado persona, @RequestParam("id") Integer idEmpresa,HttpSession session) {
        EmpresaEntity empresa = empresaRepository.getById(idEmpresa);
        session.setAttribute("empresa",empresa);
        if (persona.getTipo() == TipoPersonaEmpresa.Socio) {
            SocioEntity s = new SocioEntity();
            s.setNombre(persona.getNombre());
            s.setApellido1(persona.getApellido1());
            s.setCalle(persona.getCalle());
            s.setNif(persona.getNif());
            s.setCp(persona.getCp());
            s.setBloqueado(persona.getBloqueado());
            s.setEmpresaByIdempresa(empresa);
            s.setFechanacimiento(persona.getFechanacimiento());
            s.setCiudad(persona.getCiudad());
            s.setEmail(persona.getEmail());
            s.setPais(persona.getPais());
            s.setPassword(persona.getPassword());
            socioRepository.save(s);

            empresa.getSociosByIdempresa().add(s);
            empresaRepository.save(empresa);
        } else if (persona.getTipo() == TipoPersonaEmpresa.Autorizado) {
            AutorizadoEntity a = new AutorizadoEntity();
            a.setNombre(persona.getNombre());
            a.setApellido1(persona.getApellido1());
            a.setCalle(persona.getCalle());
            a.setNif(persona.getNif());
            a.setCp(persona.getCp());
            a.setBloqueado(persona.getBloqueado());
            a.setEmpresaByIdempresa(empresa);
            a.setFechanacimiento(persona.getFechanacimiento());
            a.setCiudad(persona.getCiudad());
            a.setEmail(persona.getEmail());
            a.setPais(persona.getPais());
            a.setPassword(persona.getPassword());

            autorizadoRepository.save(a);

            empresa.getAutorizadosByIdempresa().add(a);
            empresaRepository.save(empresa);
        }
        return "redirect:/empresa/socios/";
    }


    @GetMapping("/bloquear")
    public String bloquearSocio(@RequestParam("id") Integer idSocio,@RequestParam("e") Integer idEmpresa, HttpSession sesion){
        SocioEntity s = this.socioRepository.getById(idSocio);

        sesion.setAttribute("empresa",empresaRepository.getById(idEmpresa));

        if(s.getBloqueado() == 1){
            s.setBloqueado(0);
        }else if(s.getBloqueado() == 0){
            s.setBloqueado(1);
        }
        socioRepository.save(s);

        return "redirect:/empresa/socios/";
    }

    @GetMapping("/borrar")
    public String borrarSocio(@RequestParam("id") Integer idSocio,@RequestParam("e") Integer idEmpresa, HttpSession sesion){
        this.socioRepository.deleteById(idSocio);

        sesion.setAttribute("empresa",empresaRepository.getById(idEmpresa));


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

        AutorizadoEntity autorizado = autorizadoRepository.autenticar(nif, password);

        Object cuenta = autorizado != null ? autorizado : socioRepository.autenticar(nif,password);

        if(cuenta == null) {
            modelo.addAttribute("error", "Usuario no encontrado");
            urlTo = "loginSocioAutorizado";

        }else{
            EmpresaEntity empresa;
            String tipoCuenta = "";
            try{
                empresa = ((AutorizadoEntity) cuenta).getEmpresaByIdempresa();
                tipoCuenta = "Autorizado";
            }catch (Exception e){
                empresa = ((SocioEntity) cuenta).getEmpresaByIdempresa();
                tipoCuenta = "Socio";

            }

            if((tipoCuenta.equals("Autorizado") && ((AutorizadoEntity) cuenta).getBloqueado() == 1) || (tipoCuenta.equals("Socio") && ((SocioEntity) cuenta).getBloqueado() == 1)) {
                modelo.addAttribute("error", String.format("Esta cuenta ha sido bloqueada, solicite el desbloqueo entrando en el siguiente enlace: <br><a href='/empresa/socios/solicitarDesbloqueo?%s'>Solicitar desbloqueo.</a>",cuenta.getClass() == AutorizadoEntity.class ? "id=" + ((AutorizadoEntity) cuenta).getId()  + "&tipo=Autorizado" : "id=" + ((SocioEntity) cuenta).getId() + "&tipo=Socio"));
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
        SocioEntity socio = this.socioRepository.getById(idSocio);

        model.addAttribute("socio", socio);

        return "datosSocio";
    }

    @PostMapping("/edit")
    public String editarAutorizado(@ModelAttribute("socio") SocioEntity socio, @RequestParam("eId") Integer empresaId, HttpSession session){

        EmpresaEntity empresa = empresaRepository.getById(empresaId);
        socio.setEmpresaByIdempresa(empresa);

        session.setAttribute("empresa", empresa);
        session.setAttribute("cuenta", socio);
        session.setAttribute("tipoCuenta","Socio");

        socioRepository.save(socio);

        return "redirect:/empresa/autorizados/" + socio.getId();
    }

    @GetMapping("/solicitarDesbloqueo")
    public String solicitarDesbloqueo(@RequestParam("id") Integer idCuenta,@RequestParam("tipo") String tipoCuenta){

        if(tipoCuenta.equals("Socio")){


        }else if(tipoCuenta.equals("Autorizado")){



        }

        return "solicitarDesbloqueo";

    }

}
