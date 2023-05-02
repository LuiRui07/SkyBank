package com.example.skybank.dto;

import java.util.Objects;

public class Divisa {
    private int iddivisa;
    private String nombre;
    private Double valor;
    private String simbolo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Divisa divisa = (Divisa) o;
        return iddivisa == divisa.iddivisa && Objects.equals(nombre, divisa.nombre) && Objects.equals(valor, divisa.valor) && Objects.equals(simbolo, divisa.simbolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddivisa, nombre, valor, simbolo);
    }

    public int getIddivisa() {
        return iddivisa;
    }

    public void setIddivisa(int iddivisa) {
        this.iddivisa = iddivisa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}
