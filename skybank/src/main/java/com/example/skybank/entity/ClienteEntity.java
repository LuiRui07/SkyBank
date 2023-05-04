package com.example.skybank.entity;

import com.example.skybank.dto.Cliente;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "skybank", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcliente", nullable = false)
    private int idcliente;
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
    @Column(name = "apellido2", nullable = true, length = 100)
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
    private Integer numero;
    @Basic
    @Column(name = "ciudad", nullable = true, length = 100)
    private String ciudad;
    @Basic
    @Column(name = "pais", nullable = true, length = 100)
    private String pais;
    @Basic
    @Column(name = "planta", nullable = true)
    private Integer planta;
    @Basic
    @Column(name = "region", nullable = true, length = 100)
    private String region;
    @Basic
    @Column(name = "cp", nullable = false)
    private int cp;

    @Basic
    @Column(name = "verificado", nullable = false)
    private int verificado;

    @Basic
    @Column(name = "bloqueado", nullable = false)
    private int bloqueado;

    @OneToMany(mappedBy = "clienteByIdcliente")
    private List<ConversacionEntity> conversacionsByIdcliente;
    @OneToMany(mappedBy = "clienteByIdcliente")
    private List<CuentaEntity> cuentasByIdcliente;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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

    public Integer getPlanta() {
        return planta;
    }

    public void setPlanta(Integer planta) {
        this.planta = planta;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }
    public int getVerificado() {
        return verificado;
    }

    public void setVerificado(int verificado) {
        this.verificado = verificado;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return idcliente == that.idcliente && cp == that.cp && Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(nacimiento, that.nacimiento) && Objects.equals(calle, that.calle) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(numero, that.numero) && Objects.equals(ciudad, that.ciudad) && Objects.equals(pais, that.pais) && Objects.equals(planta, that.planta) && Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcliente, dni, nombre, apellido1, apellido2, nacimiento, calle, email, password, numero, ciudad, pais, planta, region, cp);
    }

    public List<ConversacionEntity> getConversacionsByIdcliente() {
        return conversacionsByIdcliente;
    }

    public void setConversacionsByIdcliente(List<ConversacionEntity> conversacionsByIdcliente) {
        this.conversacionsByIdcliente = conversacionsByIdcliente;
    }

    public List<CuentaEntity> getCuentasByIdcliente() {
        return cuentasByIdcliente;
    }

    public void setCuentasByIdcliente(List<CuentaEntity> cuentasByIdcliente) {
        this.cuentasByIdcliente = cuentasByIdcliente;
    }

    public Cliente toDTO(){
        Cliente cliente = new Cliente();
        cliente.setIdcliente(this.idcliente);
        cliente.setDni(this.dni);
        cliente.setNombre(this.nombre);
        cliente.setApellido1(this.apellido1);
        cliente.setApellido2(this.apellido2);
        cliente.setNacimiento(this.nacimiento);
        cliente.setCalle(this.calle);
        cliente.setEmail(this.email);
        cliente.setPassword(this.password);
        cliente.setNumero(this.numero);
        cliente.setCiudad(this.ciudad);
        cliente.setPais(this.pais);
        cliente.setPlanta(this.planta);
        cliente.setRegion(this.region);
        cliente.setCp(this.cp);
        cliente.setVerificado(this.verificado);
        cliente.setBloqueado(this.bloqueado);
        return cliente;
    }
}
