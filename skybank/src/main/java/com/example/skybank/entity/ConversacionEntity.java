package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "conversacion", schema = "skybank", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idconversacion", nullable = false)
    private int idconversacion;
    @Basic
    @Column(name = "cerrada", nullable = false)
    private int cerrada;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    private ClienteEntity clienteByIdcliente;
    @ManyToOne
    @JoinColumn(name = "idasistente", referencedColumnName = "idasistente", nullable = false)
    private AsistenteEntity asistenteByIdasistente;
    @OneToMany(mappedBy = "conversacionByIdconversacion")
    private List<MensajeEntity> mensajesByIdconversacion;

    public int getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(int idconversacion) {
        this.idconversacion = idconversacion;
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
        return idconversacion == that.idconversacion && cerrada == that.cerrada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idconversacion, cerrada);
    }

    public ClienteEntity getClienteByIdcliente() {
        return clienteByIdcliente;
    }

    public void setClienteByIdcliente(ClienteEntity clienteByIdcliente) {
        this.clienteByIdcliente = clienteByIdcliente;
    }

    public AsistenteEntity getAsistenteByIdasistente() {
        return asistenteByIdasistente;
    }

    public void setAsistenteByIdasistente(AsistenteEntity asistenteByIdasistente) {
        this.asistenteByIdasistente = asistenteByIdasistente;
    }

    public List<MensajeEntity> getMensajesByIdconversacion() {
        return mensajesByIdconversacion;
    }

    public void setMensajesByIdconversacion(List<MensajeEntity> mensajesByIdconversacion) {
        this.mensajesByIdconversacion = mensajesByIdconversacion;
    }
}
