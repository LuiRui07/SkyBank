package com.example.skybank.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "mensaje", schema = "skybank", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idMensaje", nullable = false)
    private Integer idMensaje;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Basic
    @Column(name = "hora", nullable = false)
    private Timestamp hora;
    @Basic
    @Column(name = "texto", nullable = false, length = 255)
    private String texto;
    @ManyToOne
    @JoinColumn(name = "idConversacion", referencedColumnName = "idConversacion", nullable = false)
    private ConversacionEntity conversacionByIdConversacion;

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntity that = (MensajeEntity) o;
        return Objects.equals(idMensaje, that.idMensaje) && Objects.equals(fecha, that.fecha) && Objects.equals(hora, that.hora) && Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje, fecha, hora, texto);
    }

    public ConversacionEntity getConversacionByIdConversacion() {
        return conversacionByIdConversacion;
    }

    public void setConversacionByIdConversacion(ConversacionEntity conversacionByIdConversacion) {
        this.conversacionByIdConversacion = conversacionByIdConversacion;
    }
}
