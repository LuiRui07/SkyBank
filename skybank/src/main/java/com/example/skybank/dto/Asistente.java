/*
@author: Pablo García Platero
*/
package com.example.skybank.dto;

import java.util.Objects;

public class Asistente {


    private int idasistente;
    private String email;
    private String password;


    public void setIdasistente(int idAsistente){idasistente = idAsistente;}
    public int getIdasistente() {
        return idasistente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Asistente asistente = (Asistente) o;
        return idasistente == asistente.idasistente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idasistente);
    }

}
