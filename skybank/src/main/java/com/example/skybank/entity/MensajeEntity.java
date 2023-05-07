
package com.example.skybank.entity;

import com.example.skybank.dto.Mensaje;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "mensaje", schema = "skybank", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idmensaje", nullable = false)
    private int idmensaje;
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
    @JoinColumn(name = "idconversacion", referencedColumnName = "idconversacion", nullable = false)
    private ConversacionEntity conversacionByIdconversacion;

    public int getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(int idmensaje) {
        this.idmensaje = idmensaje;
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
        return idmensaje == that.idmensaje && Objects.equals(fecha, that.fecha) && Objects.equals(hora, that.hora) && Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmensaje, fecha, hora, texto);
    }

    public ConversacionEntity getConversacionByIdconversacion() {
        return conversacionByIdconversacion;
    }

    public void setConversacionByIdconversacion(ConversacionEntity conversacionByIdconversacion) {
        this.conversacionByIdconversacion = conversacionByIdconversacion;
    }
    public Mensaje toDTO(){
        Mensaje mensajeDTO = new Mensaje();
        mensajeDTO.setIdMensajes(idmensaje);
        mensajeDTO.setTexto(texto);
        mensajeDTO.setHora(hora);
        mensajeDTO.setFecha(fecha);
        mensajeDTO.setConversacion(conversacionByIdconversacion);
        return mensajeDTO;
    }
}
