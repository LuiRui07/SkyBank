/*
@author: Pablo García Platero
*/

package com.example.skybank.dto;

import com.example.skybank.entity.ConversacionEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Mensaje implements Serializable {
    private Integer idMensajes;
    private Date fecha;
    private Timestamp hora;
    private String texto;

    private ConversacionEntity conversacion;

    public Integer getIdMensajes() {
        return idMensajes;
    }

    public void setIdMensajes(Integer idMensajes) {
        this.idMensajes = idMensajes;
    }

    public Timestamp getFecha() {
        return hora;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Timestamp gethora() {
        return hora;
    }

    public void setHora(Timestamp fechaHora) {
        this.hora = fechaHora;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ConversacionEntity getConversacion() {
        return conversacion;
    }
    public void setConversacion(ConversacionEntity conversacion){
        this.conversacion = conversacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje that = (Mensaje) o;
        return Objects.equals(idMensajes, that.idMensajes)&&Objects.equals(fecha, that.fecha) && Objects.equals(hora, that.hora) && Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensajes, fecha, hora, texto);
    }

}
