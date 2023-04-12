package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tipo-operacion", schema = "skybank", catalog = "")
public class TipoOperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Tipo", nullable = true, length = 45)
    private String tipo;
    @OneToMany(mappedBy = "tipoOperacionByTipoOperacionId")
    private Set<OperacionEntity> operacionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoOperacionEntity that = (TipoOperacionEntity) o;
        return id == that.id && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    public Set<OperacionEntity> getOperacionsById() {
        return operacionsById;
    }

    public void setOperacionsById(Set<OperacionEntity> operacionsById) {
        this.operacionsById = operacionsById;
    }
}
