package com.example.skybank.dto;

import java.io.Serializable;
import java.util.Objects;

public class ChatDTO implements Serializable {
    private Integer idConversacion;

    private Integer idasis;
    private Integer cerrada;
    private String nombreCliente;
    private  String nombreAsistente;
    private String dniUsuario;

    public Integer getIdConversacion() {return idConversacion;}
    public Integer getIdAsistente() {
        return idasis;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniCliente(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }
}
