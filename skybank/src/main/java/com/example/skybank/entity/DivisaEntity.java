package com.example.skybank.entity;

import com.example.skybank.dto.Divisa;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "divisa", schema = "skybank", catalog = "")
public class DivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iddivisa", nullable = false)
    private int iddivisa;
    @Basic
    @Column(name = "nombre", nullable = true, length = 45)
    private String nombre;
    @Basic
    @Column(name = "valor", nullable = true, precision = 0)
    private Double valor;
    @Basic
    @Column(name = "simbolo", nullable = true, length = 3)
    private String simbolo;
    @OneToMany(mappedBy = "divisaByDivisa")
    private List<CuentaEntity> cuentasByIddivisa;
    @OneToMany(mappedBy = "divisaByDivisa")
    private List<OperacionEntity> operacionsByIddivisa;

    public int getIddivisa() {
        return iddivisa;
    }

    public void setIddivisa(int iddivisa) {
        this.iddivisa = iddivisa;
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

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisaEntity that = (DivisaEntity) o;
        return iddivisa == that.iddivisa && Objects.equals(nombre, that.nombre) && Objects.equals(valor, that.valor) && Objects.equals(simbolo, that.simbolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddivisa, nombre, valor, simbolo);
    }

    public List<CuentaEntity> getCuentasByIddivisa() {
        return cuentasByIddivisa;
    }

    public void setCuentasByIddivisa(List<CuentaEntity> cuentasByIddivisa) {
        this.cuentasByIddivisa = cuentasByIddivisa;
    }

    public List<OperacionEntity> getOperacionsByIddivisa() {
        return operacionsByIddivisa;
    }

    public void setOperacionsByIddivisa(List<OperacionEntity> operacionsByIddivisa) {
        this.operacionsByIddivisa = operacionsByIddivisa;
    }

    public Divisa toDTO(){
        Divisa divisa = new Divisa();
        divisa.setIddivisa(this.iddivisa);
        divisa.setNombre(this.nombre);
        divisa.setSimbolo(this.simbolo);
        divisa.setValor(this.valor);
        return divisa;
    }
}
