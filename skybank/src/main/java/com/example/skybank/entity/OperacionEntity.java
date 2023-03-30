package com.example.skybank.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Operacion", schema = "skybank", catalog = "")
@IdClass(OperacionEntityPK.class)
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idoperación", nullable = false)
    private Integer idoperación;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Cuenta_idCuenta1", nullable = false)
    private Integer cuentaIdCuenta1;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "Cuenta_idCuenta", referencedColumnName = "idCuenta")
    private CuentaEntity cuentaByCuentaIdCuenta;
    @ManyToOne
    @JoinColumn(name = "Cuenta_idCuenta1", referencedColumnName = "idCuenta", nullable = false)
    private CuentaEntity cuentaByCuentaIdCuenta1;
    @ManyToOne
    @JoinColumn(name = "Gestor_idGestor", referencedColumnName = "idGestor", nullable = false)
    private GestorEntity gestorByGestorIdGestor;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionEntity that = (OperacionEntity) o;
        return Objects.equals(idoperación, that.idoperación) && Objects.equals(cuentaIdCuenta1, that.cuentaIdCuenta1) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idoperación, cuentaIdCuenta1, fecha);
    }

    public CuentaEntity getCuentaByCuentaIdCuenta() {
        return cuentaByCuentaIdCuenta;
    }

    public void setCuentaByCuentaIdCuenta(CuentaEntity cuentaByCuentaIdCuenta) {
        this.cuentaByCuentaIdCuenta = cuentaByCuentaIdCuenta;
    }

    public CuentaEntity getCuentaByCuentaIdCuenta1() {
        return cuentaByCuentaIdCuenta1;
    }

    public void setCuentaByCuentaIdCuenta1(CuentaEntity cuentaByCuentaIdCuenta1) {
        this.cuentaByCuentaIdCuenta1 = cuentaByCuentaIdCuenta1;
    }

    public GestorEntity getGestorByGestorIdGestor() {
        return gestorByGestorIdGestor;
    }

    public void setGestorByGestorIdGestor(GestorEntity gestorByGestorIdGestor) {
        this.gestorByGestorIdGestor = gestorByGestorIdGestor;
    }
}
