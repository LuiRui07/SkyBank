package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Empresa", schema = "skybank", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEmpresa", nullable = false)
    private Integer idEmpresa;
    @Basic
    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;
    @OneToMany(mappedBy = "empresaByEmpresaIdEmpresa")
    private Collection<CuentaEntity> cuentasByIdEmpresa;
    @OneToMany(mappedBy = "empresaByEmpresaIdEmpresa")
    private Collection<SocioEntity> sociosByIdEmpresa;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntity that = (EmpresaEntity) o;
        return Objects.equals(idEmpresa, that.idEmpresa) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, nombre);
    }

    public Collection<CuentaEntity> getCuentasByIdEmpresa() {
        return cuentasByIdEmpresa;
    }

    public void setCuentasByIdEmpresa(Collection<CuentaEntity> cuentasByIdEmpresa) {
        this.cuentasByIdEmpresa = cuentasByIdEmpresa;
    }

    public Collection<SocioEntity> getSociosByIdEmpresa() {
        return sociosByIdEmpresa;
    }

    public void setSociosByIdEmpresa(Collection<SocioEntity> sociosByIdEmpresa) {
        this.sociosByIdEmpresa = sociosByIdEmpresa;
    }
}
