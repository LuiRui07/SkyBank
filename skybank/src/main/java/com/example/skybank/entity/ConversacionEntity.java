package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Conversacion", schema = "skybank", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idConversacion", nullable = false)
    private Integer idConversacion;
    @Basic
    @Column(name = "Cerrada", nullable = false)
    private Integer cerrada;
    @ManyToOne
    @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente", nullable = false)
    private ClienteEntity clienteByClienteIdCliente;
    @ManyToOne
    @JoinColumn(name = "Asistente_idAsistente", referencedColumnName = "idAsistente", nullable = false)
    private AsistenteEntity asistenteByAsistenteIdAsistente;
    @OneToMany(mappedBy = "conversacionByConversacionIdConversacion")
    private Collection<MensajeEntity> mensajesByIdConversacion;

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Integer getCerrada() {
        return cerrada;
    }

    public void setCerrada(Integer cerrada) {
        this.cerrada = cerrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversacionEntity that = (ConversacionEntity) o;
        return Objects.equals(idConversacion, that.idConversacion) && Objects.equals(cerrada, that.cerrada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversacion, cerrada);
    }

    public ClienteEntity getClienteByClienteIdCliente() {
        return clienteByClienteIdCliente;
    }

    public void setClienteByClienteIdCliente(ClienteEntity clienteByClienteIdCliente) {
        this.clienteByClienteIdCliente = clienteByClienteIdCliente;
    }

    public AsistenteEntity getAsistenteByAsistenteIdAsistente() {
        return asistenteByAsistenteIdAsistente;
    }

    public void setAsistenteByAsistenteIdAsistente(AsistenteEntity asistenteByAsistenteIdAsistente) {
        this.asistenteByAsistenteIdAsistente = asistenteByAsistenteIdAsistente;
    }

    public Collection<MensajeEntity> getMensajesByIdConversacion() {
        return mensajesByIdConversacion;
    }

    public void setMensajesByIdConversacion(Collection<MensajeEntity> mensajesByIdConversacion) {
        this.mensajesByIdConversacion = mensajesByIdConversacion;
    }
}
