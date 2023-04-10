package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "empresa", schema = "skybank")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempresa", nullable = false)
    private Integer idEmpresa;
    @Basic
    @Column(name = "cif", nullable = false, length = 100)
    private String cif;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "emailcorporativo", nullable = false, length = 100)
    private String emailCorporativo;
    @Basic
    @Column(name = "passwordempresa", nullable = false, length = 100)
    private String passwordEmpresa;
    @Basic
    @Column(name = "calle", nullable = false, length = 100)
    private String calle;
    @Basic
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @Basic
    @Column(name = "planta", nullable = false)
    private Integer planta;
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
    private Integer cp;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<AutorizadoEntity> autorizadosByIdEmpresa;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<CuentaEntity> cuentasByIdEmpresa;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<SocioEntity> sociosByIdEmpresa;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public String getPasswordEmpresa() {
        return passwordEmpresa;
    }

    public void setPasswordEmpresa(String passwordEmpresa) {
        this.passwordEmpresa = passwordEmpresa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPlanta() {
        return planta;
    }

    public void setPlanta(Integer planta) {
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

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntity empresa = (EmpresaEntity) o;
        return Objects.equals(idEmpresa, empresa.idEmpresa) && Objects.equals(cif, empresa.cif) && Objects.equals(nombre, empresa.nombre) && Objects.equals(emailCorporativo, empresa.emailCorporativo) && Objects.equals(passwordEmpresa, empresa.passwordEmpresa) && Objects.equals(calle, empresa.calle) && Objects.equals(numero, empresa.numero) && Objects.equals(planta, empresa.planta) && Objects.equals(ciudad, empresa.ciudad) && Objects.equals(pais, empresa.pais) && Objects.equals(region, empresa.region) && Objects.equals(cp, empresa.cp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, cif, nombre, emailCorporativo, passwordEmpresa, calle, numero, planta, ciudad, pais, region, cp);
    }

    public List<AutorizadoEntity> getAutorizadosByIdEmpresa() {
        return autorizadosByIdEmpresa;
    }

    public void setAutorizadosByIdEmpresa(List<AutorizadoEntity> autorizadosByIdEmpresa) {
        this.autorizadosByIdEmpresa = autorizadosByIdEmpresa;
    }

    public List<CuentaEntity> getCuentasByIdEmpresa() {
        return cuentasByIdEmpresa;
    }

    public void setCuentasByIdEmpresa(List<CuentaEntity> cuentasByIdEmpresa) {
        this.cuentasByIdEmpresa = cuentasByIdEmpresa;
    }

    public List<SocioEntity> getSociosByIdEmpresa() {
        return sociosByIdEmpresa;
    }

    public void setSociosByIdEmpresa(List<SocioEntity> sociosByIdEmpresa) {
        this.sociosByIdEmpresa = sociosByIdEmpresa;
    }
}
