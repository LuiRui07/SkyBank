package com.example.skybank.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "empresa", schema = "skybank", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempresa", nullable = false)
    private int idempresa;
    @Basic
    @Column(name = "cif", nullable = false, length = 100)
    private String cif;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "emailcorporativo", nullable = false, length = 100)
    private String emailcorporativo;
    @Basic
    @Column(name = "passwordempresa", nullable = false, length = 100)
    private String passwordempresa;
    @Basic
    @Column(name = "calle", nullable = false, length = 100)
    private String calle;
    @Basic
    @Column(name = "numero", nullable = false)
    private int numero;
    @Basic
    @Column(name = "planta", nullable = false)
    private int planta;
    @Basic
    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;
    @Basic
    @Column(name = "pais", nullable = false, length = 100)
    private String pais;
    @Basic
    @Column(name = "region", nullable = true, length = 100)
    private String region;
    @Basic
    @Column(name = "cp", nullable = false)
    private int cp;
    @Basic
    @Column(name = "verificado", nullable = false)
    private int verificado;
    @OneToMany(mappedBy = "empresaByIdempresa")
    private List<AutorizadoEntity> autorizadosByIdempresa;
    @OneToMany(mappedBy = "empresaByIdempresa")
    private List<CuentaEntity> cuentasByIdempresa;
    @OneToMany(mappedBy = "empresaByIdempresa")
    private List<SocioEntity> sociosByIdempresa;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntity empresa = (EmpresaEntity) o;
        return idempresa == empresa.idempresa && numero == empresa.numero && planta == empresa.planta && cp == empresa.cp && verificado == empresa.verificado && Objects.equals(cif, empresa.cif) && Objects.equals(nombre, empresa.nombre) && Objects.equals(emailcorporativo, empresa.emailcorporativo) && Objects.equals(passwordempresa, empresa.passwordempresa) && Objects.equals(calle, empresa.calle) && Objects.equals(ciudad, empresa.ciudad) && Objects.equals(pais, empresa.pais) && Objects.equals(region, empresa.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idempresa, cif, nombre, emailcorporativo, passwordempresa, calle, numero, planta, ciudad, pais, region, cp, verificado);
    }

    public List<AutorizadoEntity> getAutorizadosByIdempresa() {
        return autorizadosByIdempresa;
    }

    public void setAutorizadosByIdempresa(List<AutorizadoEntity> autorizadosByIdempresa) {
        this.autorizadosByIdempresa = autorizadosByIdempresa;
    }

    public List<CuentaEntity> getCuentasByIdempresa() {
        return cuentasByIdempresa;
    }

    public void setCuentasByIdempresa(List<CuentaEntity> cuentasByIdempresa) {
        this.cuentasByIdempresa = cuentasByIdempresa;
    }

    public List<SocioEntity> getSociosByIdempresa() {
        return sociosByIdempresa;
    }

    public void setSociosByIdempresa(List<SocioEntity> sociosByIdempresa) {
        this.sociosByIdempresa = sociosByIdempresa;
    }

}
