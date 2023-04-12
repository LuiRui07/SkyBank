package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "gestor", schema = "skybank", catalog = "")
public class GestorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idGestor", nullable = false)
    private int idGestor;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "gestorByIdGestor")
    private Set<OperacionEntity> operacionsByIdGestor;

    public int getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(int idGestor) {
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
        return idGestor == that.idGestor && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestor, nombre);
    }

    public Set<OperacionEntity> getOperacionsByIdGestor() {
        return operacionsByIdGestor;
    }

    public void setOperacionsByIdGestor(Set<OperacionEntity> operacionsByIdGestor) {
        this.operacionsByIdGestor = operacionsByIdGestor;
    }
}
