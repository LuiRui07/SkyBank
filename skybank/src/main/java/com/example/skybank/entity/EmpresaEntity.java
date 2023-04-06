package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "empresa", schema = "skybank", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEmpresa", nullable = false)
    private Integer idEmpresa;
    @Basic
    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "EmailCorporativo", nullable = true, length = 100)
    private String emailCorporativo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntity that = (EmpresaEntity) o;
        return Objects.equals(idEmpresa, that.idEmpresa) && Objects.equals(nombre, that.nombre) && Objects.equals(emailCorporativo, that.emailCorporativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, nombre, emailCorporativo);
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
