package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "empresa", schema = "skybank", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idempresa", nullable = false)
    private int idEmpresa;
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
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<AutorizadoEntity> autorizadosByIdEmpresa;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<CuentaEntity> cuentasByIdEmpresa;
    @OneToMany(mappedBy = "empresaByIdEmpresa", fetch = FetchType.EAGER)
    private List<SocioEntity> sociosByIdEmpresa;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
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
        return idEmpresa == empresa.idEmpresa && numero == empresa.numero && planta == empresa.planta && cp == empresa.cp && verificado == empresa.verificado && Objects.equals(cif, empresa.cif) && Objects.equals(nombre, empresa.nombre) && Objects.equals(emailCorporativo, empresa.emailCorporativo) && Objects.equals(passwordEmpresa, empresa.passwordEmpresa) && Objects.equals(calle, empresa.calle) && Objects.equals(ciudad, empresa.ciudad) && Objects.equals(pais, empresa.pais) && Objects.equals(region, empresa.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, cif, nombre, emailCorporativo, passwordEmpresa, calle, numero, planta, ciudad, pais, region, cp, verificado);
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