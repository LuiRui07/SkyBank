package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cliente", schema = "skybank", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcliente", nullable = false)
    private int idCliente;
    @Basic
    @Column(name = "dni", nullable = false, length = 45)
    private String dni;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido1;

    @Basic
    @Column(name = "apellido2", nullable = false, length = 100)
    private String apellido2;
    @Basic
    @Column(name = "nacimiento", nullable = true)
    private Date nacimiento;
    @Basic
    @Column(name = "calle", nullable = false, length = 100)
    private String calle;
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Basic
    @Column(name = "numero", nullable = true)
    private int numero;

    @Basic
    @Column(name = "ciudad", nullable = true, length = 100)
    private String ciudad;

    @Basic
    @Column(name = "pais", nullable = true, length = 100)
    private String pais;

    @Basic
    @Column(name = "planta", nullable = true)
    private int planta;

    @Basic
    @Column(name = "region", nullable = true, length = 100)
    private String region;

    @Basic
    @Column(name = "CP", nullable = false)
    private int CP;

    @OneToMany(mappedBy = "clienteByIdCliente")
    private Set<ConversacionEntity> conversacionsByIdCliente;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private Set<CuentaEntity> cuentasByIdCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCp() {
        return CP;
    }

    public void setCp(int cp) {
        this.CP = cp;
    }

    public Set<ConversacionEntity> getConversacionsByIdCliente() {
        return conversacionsByIdCliente;
    }

    public void setConversacionsByIdCliente(Set<ConversacionEntity> conversacionsByIdCliente) {
        this.conversacionsByIdCliente = conversacionsByIdCliente;
    }

    public Set<CuentaEntity> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(Set<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }
}
