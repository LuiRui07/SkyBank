package com.example.skybank.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OperacionEntityPK implements Serializable {
    @Column(name = "idoperación", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idoperación;
    @Column(name = "Cuenta_idCuenta1", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cuentaIdCuenta1;

    public Integer getIdoperación() {
        return idoperación;
    }

    public void setIdoperación(Integer idoperación) {
        this.idoperación = idoperación;
    }

    public Integer getCuentaIdCuenta1() {
        return cuentaIdCuenta1;
    }

    public void setCuentaIdCuenta1(Integer cuentaIdCuenta1) {
        this.cuentaIdCuenta1 = cuentaIdCuenta1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionEntityPK that = (OperacionEntityPK) o;
        return Objects.equals(idoperación, that.idoperación) && Objects.equals(cuentaIdCuenta1, that.cuentaIdCuenta1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idoperación, cuentaIdCuenta1);
    }
}
