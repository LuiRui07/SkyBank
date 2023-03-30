package com.example.skybank.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SocioEntityPK implements Serializable {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Empresa_idEmpresa", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empresaIdEmpresa;
    @Column(name = "Socio_Empresa_idEmpresa", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer socioEmpresaIdEmpresa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpresaIdEmpresa() {
        return empresaIdEmpresa;
    }

    public void setEmpresaIdEmpresa(Integer empresaIdEmpresa) {
        this.empresaIdEmpresa = empresaIdEmpresa;
    }

    public Integer getSocioEmpresaIdEmpresa() {
        return socioEmpresaIdEmpresa;
    }

    public void setSocioEmpresaIdEmpresa(Integer socioEmpresaIdEmpresa) {
        this.socioEmpresaIdEmpresa = socioEmpresaIdEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocioEntityPK that = (SocioEntityPK) o;
        return Objects.equals(id, that.id) && Objects.equals(empresaIdEmpresa, that.empresaIdEmpresa) && Objects.equals(socioEmpresaIdEmpresa, that.socioEmpresaIdEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empresaIdEmpresa, socioEmpresaIdEmpresa);
    }
}
