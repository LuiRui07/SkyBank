package com.example.skybank.ui;

public class FiltroAsistente {
    private Byte activo;
    private String DNI;
    private String nombre;


    public Byte getActivo() {
        return activo;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
