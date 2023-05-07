
package com.example.skybank.entity;

import com.example.skybank.dto.Chat;

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
    @Column(name = "idasistente",nullable = false)
    private int idasistente;

    @Basic
    @Column(name = "idcliente",nullable = false)
    private int idcliente;

    @Basic
    @Column(name = "cerrada", nullable = true)
    private Byte cerrada;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false,  insertable = false, updatable = false)
    private ClienteEntity clienteByIdcliente;
    @ManyToOne
    @JoinColumn(name = "idasistente", referencedColumnName = "idasistente", nullable = false, insertable = false, updatable = false)
    private AsistenteEntity asistenteByIdasistente;
    @OneToMany(mappedBy = "conversacionByIdconversacion")
    private List<MensajeEntity> mensajesByIdconversacion;

    public int getIdconversacion() {
        return idconversacion;
    }

    public void setIdconversacion(int idconversacion) {
        this.idconversacion = idconversacion;
    }

    public int getIdasistente() {return idasistente;}

    public void setIdasistente(int idasistente) {
        this.idasistente = idasistente;
    }

    public int getIdcliente() {return idcliente;}

    public void setIdcliente(int idcliente) {this.idcliente = idcliente;}

    public Byte getCerrada() {
        return cerrada;
    }

    public void setCerrada(Byte cerrada) {
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
    public Chat toDTO() {
        Chat chatDTO = new Chat();
        chatDTO.setIdConversacion(this.idconversacion);
        chatDTO.setCerrado(this.cerrada);
        chatDTO.setIdcliente(this.idcliente);
        chatDTO.setIdasis(this.idasistente);

        chatDTO.setNombreAsistente(asistenteByIdasistente.getEmail());
        chatDTO.setNombreCliente(clienteByIdcliente.getNombre() + " " + clienteByIdcliente.getApellido1());
        chatDTO.setDniCliente(clienteByIdcliente.getDni());
        return chatDTO;
    }
}
