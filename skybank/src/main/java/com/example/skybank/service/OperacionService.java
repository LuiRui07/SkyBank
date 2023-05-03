/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.service;

import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.EmpresaRepository;
import com.example.skybank.dao.OperacionRepository;
import com.example.skybank.dao.TipoOperacionRepository;
import com.example.skybank.dto.Empresa;
import com.example.skybank.dto.Operacion;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.EmpresaEntity;
import com.example.skybank.entity.OperacionEntity;
import com.example.skybank.entity.TipoOperacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionService {

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private TipoOperacionRepository tipoOperacionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<List<Operacion>> obtenerTransferenciasEnviadas(Empresa empresa){
        List<List<Operacion>> transferencias  = new ArrayList<>();
        empresaService.obtenerCuentasDeEmpresa(empresa).forEach(c -> transferencias.add(this.operacionRepository.obtenerPagosNegativos(c.getIdcuenta()).stream().map(o -> o.toDTO()).toList()));
        return transferencias;
    }

    public List<List<Operacion>> obtenerTransferenciasRecibidas(Empresa empresa){
        List<List<Operacion>> transferencias  = new ArrayList<>();
        empresaService.obtenerCuentasDeEmpresa(empresa).forEach(c -> transferencias.add(this.operacionRepository.obtenerPagosPositivos(c.getIdcuenta()).stream().map(o -> o.toDTO()).toList()));
        return transferencias;
    }


    public void realizarTransferencia(Double cantidad,Integer idOrigen,Integer idDestino, String concepto){
        CuentaEntity origen = cuentaRepository.getById(idOrigen);
        CuentaEntity destino = cuentaRepository.getById(idDestino);

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
    }
}
