package com.example.skybank.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "operacion", schema = "skybank", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idoperación", nullable = false)
    private Integer idoperación;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
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

    public Integer getIdoperación() {
        return idoperación;
    }

    public void setIdoperación(Integer idoperación) {
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
        return Objects.equals(idoperación, that.idoperación) && Objects.equals(fecha, that.fecha);
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
