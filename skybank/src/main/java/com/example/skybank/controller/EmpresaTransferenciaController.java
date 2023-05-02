/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.controller;

import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.OperacionRepository;
import com.example.skybank.dao.TipoOperacionRepository;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.OperacionEntity;
import com.example.skybank.entity.TipoOperacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empresa/transferencias")
public class EmpresaTransferenciaController {

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TipoOperacionRepository tipoOperacionRepository;


    @GetMapping("/")
    public String mostrarTransferencias(Model model, HttpSession session){


        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");


        List<List<OperacionEntity>> transferenciasRecibidas = obtenerTransferenciasRecibidas(empresa);
        model.addAttribute("transferenciasRecibidas",transferenciasRecibidas);

        List<List<OperacionEntity>> transferenciasEnviadas = obtenerTransferenciasEnviadas(empresa);
        model.addAttribute("transferenciasEnviadas",transferenciasEnviadas);

        model.addAttribute("empresa",empresa);

        return "transferenciasEmpresa";
    }

    @PostMapping("/nueva")
    public String nuevaTransferencia(@RequestParam("cantidad") Double cantidad, @RequestParam("IdOrigen") Integer idOrigen, @RequestParam("IdDestino") Integer idDestino,
                                     @RequestParam("concepto") String concepto, Model model, HttpSession sesion){


        CuentaEntity origen = cuentaRepository.getById(idOrigen);
        CuentaEntity destino = cuentaRepository.findById(idDestino).orElse(null);

        System.out.println(destino);

        model.addAttribute("empresa",origen.getEmpresaByIdempresa());

        sesion.setAttribute("success",null);
        sesion.setAttribute("error",null);


        if(destino == null) {
            sesion.setAttribute("error", "El Id para el destino introducido es incorrecto.");
        }else if(cantidad < 1) {
            sesion.setAttribute("error", "La cantidad introducida es negativa.");
        }else if(cantidad > origen.getSaldo()){
            sesion.setAttribute("error", "La cantidad introducida es mayor a la disponible en la cuenta.");
        }else if(origen.getDivisaByDivisa().getIddivisa() != destino.getDivisaByDivisa().getIddivisa()){
            sesion.setAttribute("error", "La cuenta destino tiene una divisa distinta a la origen (" + destino.getDivisaByDivisa().getNombre() + ") <br> Cambia tu divisa para realizar la operación.");
        } else{

            TipoOperacionEntity tipo = tipoOperacionRepository.getById(1);

            OperacionEntity operacionOrigen = new OperacionEntity();
            operacionOrigen.setTipoOperacionByTipopperacionid(tipo);

            operacionOrigen.setCantidad(-1 * cantidad);
            operacionOrigen.setCuentaByIdcuenta(origen);
            operacionOrigen.setCuentaByIdcuenta2(destino);
            operacionOrigen.setFecha(new Date(System.currentTimeMillis()));
            operacionOrigen.setDivisaByDivisa(origen.getDivisaByDivisa());
            operacionOrigen.setConcepto(concepto);

            operacionRepository.save(operacionOrigen);


            OperacionEntity operacionDestino = new OperacionEntity();
            operacionDestino.setTipoOperacionByTipopperacionid(tipo);

            operacionDestino.setCantidad(cantidad);
            operacionDestino.setCuentaByIdcuenta(origen);
            operacionDestino.setCuentaByIdcuenta2(destino);
            operacionDestino.setFecha(new Date(System.currentTimeMillis()));
            operacionDestino.setConcepto(concepto);


            operacionDestino.setDivisaByDivisa(origen.getDivisaByDivisa());

            operacionRepository.save(operacionDestino);

            origen.getOperacionsByIdcuenta().add(operacionOrigen);
            origen.setSaldo(origen.getSaldo() - cantidad);

            cuentaRepository.save(origen);


            destino.getOperacionsByIdcuenta().add(operacionDestino);
            destino.setSaldo(destino.getSaldo() + cantidad);


            cuentaRepository.save(destino);


            sesion.setAttribute("success","Se ha realizado correctamente la transferencia de " + cantidad + " " + origen.getDivisaByDivisa().getSimbolo() +
                    " a la cuenta con id: " + idDestino);
        }
        sesion.setAttribute("empresa",origen.getEmpresaByIdempresa());

        return  "redirect:/empresa/transferencias/";


    }

    private List<List<OperacionEntity>> obtenerTransferenciasEnviadas(EmpresaEntity empresa){
        List<List<OperacionEntity>> transferencias  = new ArrayList<>();

        empresa.getCuentasByIdempresa().forEach(c -> transferencias.add(this.operacionRepository.obtenerPagosNegativos(c.getIdcuenta())));
        return transferencias;
    }

    private List<List<OperacionEntity>> obtenerTransferenciasRecibidas(EmpresaEntity empresa){
        List<List<OperacionEntity>> transferencias  = new ArrayList<>();

        empresa.getCuentasByIdempresa().forEach(c -> transferencias.add(this.operacionRepository.obtenerPagosPositivos(c.getIdcuenta())));
        return transferencias;
    }

}


