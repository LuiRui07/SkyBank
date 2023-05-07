/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.controller;

import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.OperacionRepository;
import com.example.skybank.dao.TipoOperacionRepository;
import com.example.skybank.dto.Cuenta;
import com.example.skybank.dto.Empresa;
import com.example.skybank.dto.Operacion;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.OperacionEntity;
import com.example.skybank.entity.TipoOperacionEntity;
import com.example.skybank.service.CuentaService;
import com.example.skybank.service.EmpresaService;
import com.example.skybank.service.OperacionService;
import com.example.skybank.ui.FiltroTransferenciasEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empresa/transferencias")
public class EmpresaTransferenciaController {

    @Autowired
    private OperacionService operacionService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/")
    public String mostrarTransferencias(Model model, HttpSession session){
        return filtrarTransferencias(null,model,session);
    }

    @PostMapping("/filtrado")
    public String mostrarTransferenciasFiltradas(@ModelAttribute("filtro") FiltroTransferenciasEmpresa filtro, Model model, HttpSession session){
        return filtrarTransferencias(filtro,model,session);
    }

    private String filtrarTransferencias(FiltroTransferenciasEmpresa filtro, Model model, HttpSession session){
        Empresa empresa = (Empresa) session.getAttribute("empresa");
        List<List<Operacion>> transferenciasRecibidas = new ArrayList<>();
        List<List<Operacion>> transferenciasEnviadas = new ArrayList<>();
        if(filtro == null || (filtro != null && filtro.getFiltro().isEmpty())){
            model.addAttribute("filtro", new FiltroTransferenciasEmpresa());
            transferenciasRecibidas = operacionService.obtenerTransferenciasRecibidas(empresa);
            transferenciasEnviadas = operacionService.obtenerTransferenciasEnviadas(empresa);

        }else{
            model.addAttribute("filtro",filtro);
            transferenciasRecibidas = operacionService.obtenerTransferenciasRecibidasFiltradas(empresa,filtro);
            transferenciasEnviadas = operacionService.obtenerTransferenciasEnviadasFiltradas(empresa,filtro);
        }

        model.addAttribute("transferenciasRecibidas",transferenciasRecibidas);
        model.addAttribute("transferenciasEnviadas",transferenciasEnviadas);
        model.addAttribute("cuentasEmpresa",empresaService.obtenerCuentasDeEmpresa(empresa));


        model.addAttribute("empresa",empresa);


        return "transferenciasEmpresa";

    }

    @PostMapping("/nueva")
    public String nuevaTransferencia(@RequestParam("cantidad") Double cantidad, @RequestParam("IdOrigen") Integer idOrigen, @RequestParam("IdDestino") Integer idDestino,
                                     @RequestParam("concepto") String concepto, Model model, HttpSession sesion){

        Cuenta origen = cuentaService.obtenerCuentaPorId(idOrigen);
        Cuenta destino = cuentaService.obtenerCuentaPorId(idDestino);



        sesion.setAttribute("success",null);
        sesion.setAttribute("error",null);


        if(destino == null) {
            sesion.setAttribute("error", "El Id para el destino introducido es incorrecto.");
        }else if(cantidad < 1) {
            sesion.setAttribute("error", "La cantidad introducida es negativa.");
        }else if(cantidad > origen.getSaldo()){
            sesion.setAttribute("error", "La cantidad introducida es mayor a la disponible en la cuenta.");
        }else if(origen.getDivisa().getIddivisa() != destino.getDivisa().getIddivisa()){
            sesion.setAttribute("error", "La cuenta destino tiene una divisa distinta a la origen (" + destino.getDivisa().getNombre() + ") <br> Cambia tu divisa para realizar la operación.");
        } else{

            operacionService.realizarTransferencia(cantidad,idOrigen,idDestino,concepto);

            sesion.setAttribute("success","Se ha realizado correctamente la transferencia de " + cantidad + " " + origen.getDivisa().getSimbolo() +
                    " a la cuenta con id: " + idDestino);
        }
        sesion.setAttribute("empresa",empresaService.obtenerEmpresaPorIdCuenta(origen.getIdcuenta()));

        return  "redirect:/empresa/transferencias/";


    }

}


