/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.dto;

import java.util.Objects;

public class Empresa {
    private int idempresa;
    private String cif;
    private String nombre;
    private String emailcorporativo;
    private String passwordempresa;
    private String calle;
    private int numero;
    private int planta;
    private String ciudad;
    private String pais;
    private String region;
    private int cp;
    private int verificado;

    private int bloqueada;

    public int getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(int bloquada) {
        this.bloqueada = bloquada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return idempresa == empresa.idempresa && numero == empresa.numero && planta == empresa.planta && cp == empresa.cp && verificado == empresa.verificado && Objects.equals(cif, empresa.cif) && Objects.equals(nombre, empresa.nombre) && Objects.equals(emailcorporativo, empresa.emailcorporativo) && Objects.equals(passwordempresa, empresa.passwordempresa) && Objects.equals(calle, empresa.calle) && Objects.equals(ciudad, empresa.ciudad) && Objects.equals(pais, empresa.pais) && Objects.equals(region, empresa.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idempresa, cif, nombre, emailcorporativo, passwordempresa, calle, numero, planta, ciudad, pais, region, cp, verificado);
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmailcorporativo() {
        return emailcorporativo;
    }

    public void setEmailcorporativo(String emailcorporativo) {
        this.emailcorporativo = emailcorporativo;
    }

    public String getPasswordempresa() {
        return passwordempresa;
    }

    public void setPasswordempresa(String passwordempresa) {
        this.passwordempresa = passwordempresa;
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

    public int getVerificado() {
        return verificado;
    }

    public void setVerificado(int verificado) {
        this.verificado = verificado;
    }
}
