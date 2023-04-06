package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gestor", schema = "skybank", catalog = "")
public class GestorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idGestor", nullable = false)
    private Integer idGestor;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "gestorByIdGestor")
    private List<OperacionEntity> operacionsByIdGestor;

    public Integer getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(Integer idGestor) {
        this.idGestor = idGestor;
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
        GestorEntity that = (GestorEntity) o;
        return Objects.equals(idGestor, that.idGestor) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestor, nombre);
    }

    public List<OperacionEntity> getOperacionsByIdGestor() {
        return operacionsByIdGestor;
    }

    public void setOperacionsByIdGestor(List<OperacionEntity> operacionsByIdGestor) {
        this.operacionsByIdGestor = operacionsByIdGestor;
    }
}
