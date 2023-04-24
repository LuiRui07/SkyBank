package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "conversacion", schema = "skybank", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idConversacion", nullable = false)
    private int idConversacion;
    @Basic
    @Column(name = "Cerrada", nullable = false)
    private int cerrada;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", nullable = false)
    private ClienteEntity clienteByIdCliente;
    @ManyToOne
    @JoinColumn(name = "idAsistente", referencedColumnName = "idAsistente", nullable = false)
    private AsistenteEntity asistenteByIdAsistente;
    @OneToMany(mappedBy = "conversacionByIdConversacion")
    private Set<MensajeEntity> mensajesByIdConversacion;

    public int getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(int idConversacion) {
        this.idConversacion = idConversacion;
    }

    public int getCerrada() {
        return cerrada;
    }

    public void setCerrada(int cerrada) {
        this.cerrada = cerrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversacionEntity that = (ConversacionEntity) o;
        return idConversacion == that.idConversacion && cerrada == that.cerrada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversacion, cerrada);
    }

    public ClienteEntity getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(ClienteEntity clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }

    public AsistenteEntity getAsistenteByIdAsistente() {
        return asistenteByIdAsistente;
    }

    public void setAsistenteByIdAsistente(AsistenteEntity asistenteByIdAsistente) {
        this.asistenteByIdAsistente = asistenteByIdAsistente;
    }

    public Set<MensajeEntity> getMensajesByIdConversacion() {
        return mensajesByIdConversacion;
    }

    public void setMensajesByIdConversacion(Set<MensajeEntity> mensajesByIdConversacion) {
        this.mensajesByIdConversacion = mensajesByIdConversacion;
    }
}
