package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Socio", schema = "skybank", catalog = "")
@IdClass(SocioEntityPK.class)
public class SocioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Empresa_idEmpresa", nullable = false)
    private Integer empresaIdEmpresa;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Socio_Empresa_idEmpresa", nullable = false)
    private Integer socioEmpresaIdEmpresa;
    @ManyToOne
    @JoinColumn(name = "Empresa_idEmpresa", referencedColumnName = "idEmpresa", nullable = false)
    private EmpresaEntity empresaByEmpresaIdEmpresa;
    @ManyToOne
    @JoinColumn(name = "Empresa_idEmpresa", referencedColumnName = "Socio_Empresa_idEmpresa", nullable = false)
    private SocioEntity socioByEmpresaIdEmpresa;
    @OneToMany(mappedBy = "socioByEmpresaIdEmpresa")
    private Collection<SocioEntity> socioby;

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
        SocioEntity that = (SocioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(empresaIdEmpresa, that.empresaIdEmpresa) && Objects.equals(socioEmpresaIdEmpresa, that.socioEmpresaIdEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empresaIdEmpresa, socioEmpresaIdEmpresa);
    }

    public EmpresaEntity getEmpresaByEmpresaIdEmpresa() {
        return empresaByEmpresaIdEmpresa;
    }

    public void setEmpresaByEmpresaIdEmpresa(EmpresaEntity empresaByEmpresaIdEmpresa) {
        this.empresaByEmpresaIdEmpresa = empresaByEmpresaIdEmpresa;
    }

    public SocioEntity getSocioByEmpresaIdEmpresa() {
        return socioByEmpresaIdEmpresa;
    }

    public void setSocioByEmpresaIdEmpresa(SocioEntity socioByEmpresaIdEmpresa) {
        this.socioByEmpresaIdEmpresa = socioByEmpresaIdEmpresa;
    }

    public Collection<SocioEntity> getSocioby() {
        return socioby;
    }

    public void setSocioby(Collection<SocioEntity> socioby) {
        this.socioby = socioby;
    }
}
