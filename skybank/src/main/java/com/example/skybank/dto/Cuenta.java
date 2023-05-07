/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.dto;

import java.util.Objects;

public class Cuenta {
    private int idcuenta;
    private double saldo;
    private int sospechosa;
    private int activa;
    private Divisa divisa;

    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return idcuenta == cuenta.idcuenta && Double.compare(cuenta.saldo, saldo) == 0 && sospechosa == cuenta.sospechosa && activa == cuenta.activa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcuenta, saldo, sospechosa, activa);
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getSospechosa() {
        return sospechosa;
    }

    public void setSospechosa(int sospechosa) {
        this.sospechosa = sospechosa;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }
}
