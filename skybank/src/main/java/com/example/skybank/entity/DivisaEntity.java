package com.example.skybank.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "divisa", schema = "skybank", catalog = "")
public class DivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iddivisa", nullable = false)
    private int idDivisa;
    @Basic
    @Column(name = "nombre", length = 45)
    private String nombre;

    @Basic
    @Column(name = "valor")
    private Double valor;

    public int getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(int idDivisa) {
        this.idDivisa = idDivisa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
