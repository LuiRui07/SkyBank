package com.example.skybank.dto;

import java.util.Objects;

public class Gestor {
    private int idgestor;
    private String nombre;
    private String dni;
    private String password;

    public int getIdgestor() {
        return idgestor;
    }

    public void setIdgestor(int idgestor) {
        this.idgestor = idgestor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestor gestor = (Gestor) o;
        return idgestor == gestor.idgestor && Objects.equals(nombre, gestor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idgestor, nombre);
    }

}
