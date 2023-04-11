package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socio", schema = "skybank", catalog = "")
public class SocioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "nif", nullable = false, length = 45)
    private String nif;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa", nullable = false)
    private EmpresaEntity empresaByIdEmpresa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocioEntity that = (SocioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(nif, that.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, nif);
    }

    public EmpresaEntity getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(EmpresaEntity empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }
}
