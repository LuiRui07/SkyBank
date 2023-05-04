/*
    @autor: Luis Ruiz Nuñez
 */
package com.example.skybank.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

public class Cliente {

    private int idcliente;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date nacimiento;
    private String calle;
    private String email;
    private String password;
    private Integer numero;
    private String ciudad;
    private String pais;
    private Integer planta;
    private String region;
    private int cp;
    private int verificado;
    private int bloqueado;
    private int solicitudactivacion;


    public int getSolicitudactivacion() {
        return solicitudactivacion;
    }

    public void setSolicitudactivacion(int solicitudactivacion) {
        this.solicitudactivacion = solicitudactivacion;
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
        Cliente cliente = (Cliente) o;
        return idcliente == cliente.idcliente && cp == cliente.cp && Objects.equals(dni, cliente.dni) && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido1, cliente.apellido1) && Objects.equals(apellido2, cliente.apellido2) && Objects.equals(nacimiento, cliente.nacimiento) && Objects.equals(calle, cliente.calle) && Objects.equals(email, cliente.email) && Objects.equals(password, cliente.password) && Objects.equals(numero, cliente.numero) && Objects.equals(ciudad, cliente.ciudad) && Objects.equals(pais, cliente.pais) && Objects.equals(planta, cliente.planta) && Objects.equals(region, cliente.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcliente, dni, nombre, apellido1, apellido2, nacimiento, calle, email, password, numero, ciudad, pais, planta, region, cp);
    }

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
}
