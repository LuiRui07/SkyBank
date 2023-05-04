package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gestor", schema = "skybank", catalog = "")
public class GestorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idgestor", nullable = false)
    private int idgestor;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Basic
    @Column(name = "DNI", nullable = false, length = 9)
    private String dni;

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @OneToMany(mappedBy = "gestorByIdgestor")
    private List<OperacionEntity> operacionsByIdgestor;

    public int getIdgestor() {
        return idgestor;
    }

    public void setIdgestor(int idgestor) {
        this.idgestor = idgestor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorEntity that = (GestorEntity) o;
        return idgestor == that.idgestor && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idgestor, nombre);
    }

    public List<OperacionEntity> getOperacionsByIdgestor() {
        return operacionsByIdgestor;
    }

    public void setOperacionsByIdgestor(List<OperacionEntity> operacionsByIdgestor) {
        this.operacionsByIdgestor = operacionsByIdgestor;
    }
}
