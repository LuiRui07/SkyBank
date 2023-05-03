/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.dto;

import java.sql.Date;
import java.util.Objects;

public class Autorizado {
    private int id;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechanacimiento;
    private int bloqueado;
    private String email;
    private String password;
    private String calle;
    private int numero;
    private int planta;
    private String ciudad;
    private String pais;
    private String region;
    private int cp;

    private int solicituddesbloqueo;

    public int getSolicituddesbloqueo() {
        return solicituddesbloqueo;
    }

    public void setSolicituddesbloqueo(int solicituddesbloqueo) {
        this.solicituddesbloqueo = solicituddesbloqueo;
    }

    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autorizado that = (Autorizado) o;
        return id == that.id && bloqueado == that.bloqueado && numero == that.numero && planta == that.planta && cp == that.cp && Objects.equals(nif, that.nif) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(fechanacimiento, that.fechanacimiento) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(calle, that.calle) && Objects.equals(ciudad, that.ciudad) && Objects.equals(pais, that.pais) && Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, nombre, apellido1, apellido2, fechanacimiento, bloqueado, email, password, calle, numero, planta, ciudad, pais, region, cp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
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

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
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
