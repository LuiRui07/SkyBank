/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.dto;

import java.util.Date;
import java.util.Objects;

public class Operacion {
    private int idoperacion;
    private Date fecha;
    private double cantidad;
    private String concepto;
    private TipoOperacion tipoOperacion;
    private Divisa divisa;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operacion operacion = (Operacion) o;
        return idoperacion == operacion.idoperacion && Double.compare(operacion.cantidad, cantidad) == 0 && Objects.equals(fecha, operacion.fecha) && Objects.equals(concepto, operacion.concepto) && Objects.equals(tipoOperacion, operacion.tipoOperacion) && Objects.equals(divisa, operacion.divisa) && Objects.equals(cuentaOrigen, operacion.cuentaOrigen) && Objects.equals(cuentaDestino, operacion.cuentaDestino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idoperacion, fecha, cantidad, concepto, tipoOperacion, divisa, cuentaOrigen, cuentaDestino);
    }

    public int getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(int idoperacion) {
        this.idoperacion = idoperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
