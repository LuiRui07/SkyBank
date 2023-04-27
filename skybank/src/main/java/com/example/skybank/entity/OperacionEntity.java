package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "operacion", schema = "skybank", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idoperación", nullable = false)
    private int idoperación;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Basic
    @Column(name = "divisa", length = 45)
    private String divisa;

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    @ManyToOne
    @JoinColumn(name = "idGestor", referencedColumnName = "idGestor")
    private GestorEntity gestorByIdGestor;
    @ManyToOne
    @JoinColumn(name = "TipoOperacionId", referencedColumnName = "id", nullable = false)
    private TipoOperacionEntity tipoOperacionByTipoOperacionId;
    @ManyToOne
    @JoinColumn(name = "idCuenta2", referencedColumnName = "idCuenta")
    private CuentaEntity cuentaByIdCuenta2;
    @ManyToOne
    @JoinColumn(name = "idCuenta", referencedColumnName = "idCuenta", nullable = false)
    private CuentaEntity cuentaByIdCuenta;

    public int getIdoperación() {
        return idoperación;
    }

    public void setIdoperación(int idoperación) {
        this.idoperación = idoperación;
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
        return idoperación == that.idoperación && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idoperación, fecha);
    }

    public GestorEntity getGestorByIdGestor() {
        return gestorByIdGestor;
    }

    public void setGestorByIdGestor(GestorEntity gestorByIdGestor) {
        this.gestorByIdGestor = gestorByIdGestor;
    }

    public TipoOperacionEntity getTipoOperacionByTipoOperacionId() {
        return tipoOperacionByTipoOperacionId;
    }

    public void setTipoOperacionByTipoOperacionId(TipoOperacionEntity tipoOperacionByTipoOperacionId) {
        this.tipoOperacionByTipoOperacionId = tipoOperacionByTipoOperacionId;
    }

    public CuentaEntity getCuentaByIdCuenta2() {
        return cuentaByIdCuenta2;
    }

    public void setCuentaByIdCuenta2(CuentaEntity cuentaByIdCuenta2) {
        this.cuentaByIdCuenta2 = cuentaByIdCuenta2;
    }

    public CuentaEntity getCuentaByIdCuenta() {
        return cuentaByIdCuenta;
    }

    public void setCuentaByIdCuenta(CuentaEntity cuentaByIdCuenta) {
        this.cuentaByIdCuenta = cuentaByIdCuenta;
    }
}
