package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "skybank", catalog = "")
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
    @Basic
    @Column(name = "Direccion", nullable = true, length = 100)
    private String direccion;
    @Basic
    @Column(name = "Email", nullable = true, length = 100)
    private String email;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private List<ConversacionEntity> conversacionsByIdCliente;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private List<CuentaEntity> cuentasByIdCliente;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(idCliente, that.idCliente) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(edad, that.edad) && Objects.equals(direccion, that.direccion) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombre, apellidos, edad, direccion, email);
    }

    public List<ConversacionEntity> getConversacionsByIdCliente() {
        return conversacionsByIdCliente;
    }

    public void setConversacionsByIdCliente(List<ConversacionEntity> conversacionsByIdCliente) {
        this.conversacionsByIdCliente = conversacionsByIdCliente;
    }

    public List<CuentaEntity> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(List<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }
}
