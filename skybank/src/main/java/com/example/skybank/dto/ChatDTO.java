package com.example.skybank.dto;

import java.io.Serializable;
import java.util.Objects;

public class ChatDTO implements Serializable {
    private Integer idConversacion;

    private Integer idAsistente;
    private Integer cerrada;
    private String nombreUsuario;
    private  String nombreAsistente;
    private String dniUsuario;

    public Integer getIdConversacion() {return idConversacion;}
    public Integer getIdAsistente() {
        return idConversacion;
    }
    public void setIdConversacion(Integer idChat) {
        this.idConversacion = idChat;
    }

    public int getCerrada() {
        return cerrada;
    }

    public void setCerrado(int cerrado) {
        this.cerrada = cerrado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversacion,cerrada);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatDTO chatDTO = (ChatDTO) o;
        return Objects.equals(idConversacion, chatDTO.idConversacion) && Objects.equals(cerrada, chatDTO.cerrada);
    }

    public String getNombreAsistente() {
        return nombreAsistente;
    }

    public void setNombreAsistente(String nombreAsistente) {
        this.nombreAsistente = nombreAsistente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }
}
