/*
    @autor: José Luis López Ruiz(75%) y Luis Ruiz Nuñez(25%).
 */

package com.example.skybank.service;

import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.DivisaRepository;
import com.example.skybank.dto.Cuenta;
import com.example.skybank.dto.Divisa;
import com.example.skybank.dto.Empresa;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.DivisaEntity;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private DivisaRepository divisaRepository;

    public void cambiarDivisa(Integer idCuenta, Integer idDivisa){
        DivisaEntity d = divisaRepository.getById(idDivisa);
        CuentaEntity c = cuentaRepository.getById(idCuenta);

        Double saldo = c.getSaldo();
        Double nuevoSaldo;
        if(c.getDivisaByDivisa().getIddivisa() == 1){
            nuevoSaldo = Math.ceil((double) saldo * d.getValor());

        }else{
            nuevoSaldo = Math.ceil((double) saldo / c.getDivisaByDivisa().getValor());
        }

        c.setSaldo(nuevoSaldo);
        c.setDivisaByDivisa(d);
        cuentaRepository.save(c);
    }

    public Cuenta obtenerCuentaPorId(Integer idCuenta){
        CuentaEntity c = cuentaRepository.findById(idCuenta).orElse(null);
        return c == null ? null : c.toDTO();
    }

    public List<Cuenta> getCuentasByDivisa(Divisa divisa, int idcuenta){
        List<Cuenta> cuentas = cuentaRepository.findByDivisa(divisa.getIddivisa(), idcuenta).stream().map(c -> c.toDTO()).toList();
        return cuentas;
    }

}
