package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Gestor", schema = "skybank", catalog = "")
public class GestorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idGestor", nullable = false)
    private Integer idGestor;
    @OneToMany(mappedBy = "gestorByGestorIdGestor")
    private Collection<OperacionEntity> operacionsByIdGestor;

    public Integer getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(Integer idGestor) {
        this.idGestor = idGestor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorEntity that = (GestorEntity) o;
        return Objects.equals(idGestor, that.idGestor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestor);
    }

    public Collection<OperacionEntity> getOperacionsByIdGestor() {
        return operacionsByIdGestor;
    }

    public void setOperacionsByIdGestor(Collection<OperacionEntity> operacionsByIdGestor) {
        this.operacionsByIdGestor = operacionsByIdGestor;
    }
}
