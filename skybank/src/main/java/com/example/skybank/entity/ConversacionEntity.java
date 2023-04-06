package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "conversacion", schema = "skybank", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idConversacion", nullable = false)
    private Integer idConversacion;
    @Basic
    @Column(name = "Cerrada", nullable = false)
    private Integer cerrada;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", nullable = false)
    private ClienteEntity clienteByIdCliente;
    @ManyToOne
    @JoinColumn(name = "idAsistente", referencedColumnName = "idAsistente", nullable = false)
    private AsistenteEntity asistenteByIdAsistente;
    @OneToMany(mappedBy = "conversacionByIdConversacion")
    private List<MensajeEntity> mensajesByIdConversacion;

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

    public List<MensajeEntity> getMensajesByIdConversacion() {
        return mensajesByIdConversacion;
    }

    public void setMensajesByIdConversacion(List<MensajeEntity> mensajesByIdConversacion) {
        this.mensajesByIdConversacion = mensajesByIdConversacion;
    }
}
