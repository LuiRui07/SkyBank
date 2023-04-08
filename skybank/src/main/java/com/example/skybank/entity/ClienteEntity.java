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
    @Column(name = "DNI", nullable = false, length = 45)
    private String dni;
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
    @Column(name = "Direccion", nullable = false, length = 100)
    private String direccion;
    @Basic
    @Column(name = "Email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(idCliente, that.idCliente) && Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(edad, that.edad) && Objects.equals(direccion, that.direccion) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, dni, nombre, apellidos, edad, direccion, email, password);
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
