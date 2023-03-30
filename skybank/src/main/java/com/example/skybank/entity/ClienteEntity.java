package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Cliente", schema = "skybank", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;
    @Basic
    @Column(name = "Nombre", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "Apellidos", nullable = false, length = 100)
    private String apellidos;
    @Basic
    @Column(name = "Edad", nullable = false)
    private Integer edad;
    @OneToMany(mappedBy = "clienteByClienteIdCliente")
    private Collection<ConversacionEntity> conversacionsByIdCliente;
    @OneToMany(mappedBy = "clienteByClienteIdCliente")
    private Collection<CuentaEntity> cuentasByIdCliente;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(idCliente, that.idCliente) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(edad, that.edad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombre, apellidos, edad);
    }

    public Collection<ConversacionEntity> getConversacionsByIdCliente() {
        return conversacionsByIdCliente;
    }

    public void setConversacionsByIdCliente(Collection<ConversacionEntity> conversacionsByIdCliente) {
        this.conversacionsByIdCliente = conversacionsByIdCliente;
    }

    public Collection<CuentaEntity> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(Collection<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }
}
