package com.example.skybank.ui;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FiltroOperaciones {
    private String tipo;

    private Double Max;

    private Double Min;

    private int idCuenta;

    private OrdenOperaciones ordenOperaciones;

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date desde;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Hasta;

    public FiltroOperaciones(String tipo, Double max, Double min, Date desde, Date hasta) {
        this.tipo = tipo;
        Max = max;
        Min = min;
        this.desde = desde;
        Hasta = hasta;
    }

    public FiltroOperaciones() {
        tipo = "";
        Max = null;
        Min = null;
        desde = null;
        Hasta = null;
        ordenOperaciones = null;
    }

    public OrdenOperaciones getOrdenOperaciones() {
        return ordenOperaciones;
    }

    public void setOrdenOperaciones(OrdenOperaciones ordenOperaciones) {
        this.ordenOperaciones = ordenOperaciones;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMax() {
        return Max;
    }

    public void setMax(Double max) {
        Max = max;
    }

    public Double getMin() {
        return Min;
    }

    public void setMin(Double min) {
        Min = min;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return Hasta;
    }

    public void setHasta(Date hasta) {
        Hasta = hasta;
    }
}
