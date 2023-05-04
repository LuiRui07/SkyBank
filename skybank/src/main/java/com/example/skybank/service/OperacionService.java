/*
    @autor: José Luis López Ruiz(10%) y Luis Ruiz Nuñez(90%).
 */

package com.example.skybank.service;

import com.example.skybank.dao.*;
import com.example.skybank.dto.*;
import com.example.skybank.entity.*;
import com.example.skybank.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DecimalFormat;
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

    @Autowired
    private DivisaRepository divisaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

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

    public List<Operacion> obtenerOperacaionesCliente(Cuenta cuenta){
        List<Operacion> operaciones = operacionRepository.findbyAccount(cuenta.getIdcuenta()).stream().map(o -> o.toDTO()).toList();
        return operaciones;
    }

    public List<TipoOperacion> obtenerTodosTiposOperacion (){
        return tipoOperacionRepository.findAll().stream().map(t -> t.toDTO()).toList();
    }

    public List<Operacion> filtrarPorTipo(FiltroOperaciones filtro, int idcuenta){
        return operacionRepository.filtrarPorTipo(filtro.getTipo(),idcuenta).stream().map(o -> o.toDTO()).toList();
    }

    public List<Operacion> filtrarMax (FiltroOperaciones filtro, int idcuenta){
        return operacionRepository.filtrarMax(filtro.getMax(),idcuenta).stream().map(o -> o.toDTO()).toList();
    }

    public List<Operacion> filtrarMin (FiltroOperaciones filtro, int idcuenta){
        return operacionRepository.filtrarMin(filtro.getMin(),idcuenta).stream().map(o -> o.toDTO()).toList();
    }

    public List<Operacion> filtrarDesde (FiltroOperaciones filtro, int idcuenta){
        return operacionRepository.filtrarDesde(filtro.getDesde(),idcuenta).stream().map(o -> o.toDTO()).toList();
    }

    public List<Operacion> filtrarHasta (FiltroOperaciones filtro, int idcuenta){
        return operacionRepository.filtrarHasta(filtro.getHasta(),idcuenta).stream().map(o -> o.toDTO()).toList();
    }

    public void realizarTransferenciaCliente(Operacion operacionForm){
        TipoOperacionEntity tipo = tipoOperacionRepository.findById(1).orElse(null);
        CuentaEntity origen = cuentaRepository.findById(operacionForm.getCuentaOrigen().getIdcuenta()).orElse(null);
        CuentaEntity destino = cuentaRepository.findById((operacionForm.getCuentaDestino().getIdcuenta())).orElse(null);


        OperacionEntity op = new OperacionEntity();
        op.setCantidad(operacionForm.getCantidad());
        op.setConcepto(null);
        op.setCuentaByIdcuenta(origen);

        op.setDivisaByDivisa(origen.getDivisaByDivisa());
        op.setCuentaByIdcuenta2(destino);
        op.setTipoOperacionByTipopperacionid(tipo);
        op.setConcepto(operacionForm.getConcepto());
        op.setFecha(new java.util.Date());

        origen.quitarSaldo(operacionForm.getCantidad());
        cuentaRepository.save(origen);
        destino.anadirSaldo(operacionForm.getCantidad());
        cuentaRepository.save(destino);
        operacionRepository.save(op);
    }

    public void realizarCambioDivisa(Operacion operacionForm){
        CuentaEntity origen = cuentaRepository.findById(operacionForm.getCuentaOrigen().getIdcuenta()).orElse(null);
        DivisaEntity divisaOperacion = divisaRepository.findById(operacionForm.getDivisa().getIddivisa()).orElse(null);

        TipoOperacionEntity tipo = tipoOperacionRepository.findById(2).orElse(null);
        OperacionEntity op = new OperacionEntity();
        op.setCantidad(operacionForm.getCantidad());
        op.setConcepto(null);
        op.setCuentaByIdcuenta(origen);
        op.setDivisaByDivisa(divisaOperacion);
        op.setTipoOperacionByTipopperacionid(tipo);
        op.setFecha(new java.util.Date());


        ClienteEntity cliente = origen.getClienteByIdcliente();
        CuentaEntity destino = tieneDivisa(cliente,divisaOperacion);

        origen.quitarSaldo(operacionForm.getCantidad());
        cuentaRepository.save(origen);
        Double saldoN = (operacionForm.getCantidad()/origen.getDivisaByDivisa().getValor() ) * divisaOperacion.getValor();
        DecimalFormat formato = new DecimalFormat("#.##");
        String aproximado = formato.format(saldoN);
        aproximado = aproximado.replace(',', '.');
        Double saldoNuevo = Double.parseDouble(aproximado);
        if (destino != null){
            destino.anadirSaldo(saldoNuevo);
            op.setCuentaByIdcuenta2(destino);
            cuentaRepository.save(destino);
        } else {
            CuentaEntity cuentaNueva = new CuentaEntity();
            cuentaNueva.setClienteByIdcliente(cliente);
            cuentaNueva.setSaldo(saldoNuevo);
            cuentaNueva.setDivisaByDivisa(divisaOperacion);
            cuentaNueva.setSospechosa(0);
            cuentaNueva.setActiva(1);
            op.setCuentaByIdcuenta2(cuentaNueva);
            cuentaRepository.save(cuentaNueva);
        }
        operacionRepository.save(op);
    }

    private CuentaEntity tieneDivisa (ClienteEntity cliente, DivisaEntity divisa){
        CuentaEntity res = null;
        List<CuentaEntity> cuentas = cliente.getCuentasByIdcliente();
        for (CuentaEntity cuenta : cuentas){
            if (cuenta.getDivisaByDivisa() == divisa){
                res = cuenta;
            }
        }

        return res;
    }

}
