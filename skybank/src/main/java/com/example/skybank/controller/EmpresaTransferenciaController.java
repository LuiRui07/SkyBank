package com.example.skybank.controller;

import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.OperacionRepository;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.OperacionEntity;
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
import java.util.List;

@Controller
@RequestMapping("/empresa/transferencias")
public class EmpresaTransferenciaController {

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/")
    public String mostrarTransferencias(Model model, HttpSession session){


        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");

        List<List<OperacionEntity>> transferencias = obtenerTransferencias(empresa);


        model.addAttribute("transferencias",transferencias);
        model.addAttribute("empresa",empresa);

        return "transferenciasEmpresa";
    }

    @PostMapping("/nueva")
    public String nuevaTransferencia(@RequestParam("cantidad") Double cantidad, @RequestParam("IdOrigen") Integer idOrigen, @RequestParam("IdDestino") Integer idDestino,
                                     Model model, HttpSession sesion){


        CuentaEntity origen = cuentaRepository.getById(idOrigen);
        CuentaEntity destino = cuentaRepository.findById(idDestino).orElse(null);

        System.out.println(destino);

        model.addAttribute("empresa",origen.getEmpresaByIdempresa());

        List<List<OperacionEntity>> transferencias = obtenerTransferencias(origen.getEmpresaByIdempresa());
        model.addAttribute("transferencias",transferencias);

        if(destino == null) {
            model.addAttribute("error", "El Id para el destino introducido es incorrecto.");
        }else if(cantidad < 1) {
            model.addAttribute("error", "La cantidad introducida es negativa.");
        }else if(cantidad > origen.getSaldo()){
            model.addAttribute("error", "La cantidad introducida es mayor a la disponible en la cuenta.");
        } else{
            model.addAttribute("success","Se ha realizado correctamente la transferencia de " + cantidad + " " + origen.getDivisaByDivisa().getSimbolo() +
                    " a la cuenta con id: " + idDestino);
        }

        return "transferenciasEmpresa";


    }

    private List<List<OperacionEntity>> obtenerTransferencias(EmpresaEntity empresa){
        return empresa.getCuentasByIdempresa()
                .stream().map(c -> this.operacionRepository.obtenerTransferenciasCuenta(c.getIdcuenta()))
                .filter(k -> k.size() > 0).toList();
    }

}


