package com.example.skybank.entity;

import com.example.skybank.dto.Operacion;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "operacion", schema = "skybank", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idoperacion", nullable = false)
    private int idoperación;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private double cantidad;
    @Basic
    @Column(name = "operacioncol", length = 45)
    private String operacioncol;
    @Basic
    @Column(name = "concepto", length = 100)
    private String concepto;
    @ManyToOne
    @JoinColumn(name = "idgestor", referencedColumnName = "idgestor")
    private Gestor gestorByIdgestor;
    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "idtipo", nullable = false)
    private TipoOperacionEntity tipoOperacionByTipopperacionid;
    @ManyToOne
    @JoinColumn(name = "idcuenta2", referencedColumnName = "idcuenta")
    private CuentaEntity cuentaByIdcuenta2;
    @ManyToOne
    @JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta", nullable = false)
    private CuentaEntity cuentaByIdcuenta;
    @ManyToOne
    @JoinColumn(name = "divisa", referencedColumnName = "iddivisa")
    private DivisaEntity divisaByDivisa;

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getOperacioncol() {
        return operacioncol;
    }

    public void setOperacioncol(String operacioncol) {
        this.operacioncol = operacioncol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionEntity that = (OperacionEntity) o;
        return idoperación == that.idoperación && cantidad == that.cantidad && Objects.equals(fecha, that.fecha) && Objects.equals(operacioncol, that.operacioncol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idoperación, fecha, cantidad, operacioncol);
    }

    public Gestor getGestorByIdgestor() {
        return gestorByIdgestor;
    }

    public void setGestorByIdgestor(Gestor gestorByIdgestor) {
        this.gestorByIdgestor = gestorByIdgestor;
    }

    public TipoOperacionEntity getTipoOperacionByTipopperacionid() {
        return tipoOperacionByTipopperacionid;
    }

    public void setTipoOperacionByTipopperacionid(TipoOperacionEntity tipoOperacionByTipopperacionid) {
        this.tipoOperacionByTipopperacionid = tipoOperacionByTipopperacionid;
    }

    public CuentaEntity getCuentaByIdcuenta2() {
        return cuentaByIdcuenta2;
    }

    public void setCuentaByIdcuenta2(CuentaEntity cuentaByIdcuenta2) {
        this.cuentaByIdcuenta2 = cuentaByIdcuenta2;
    }

    public CuentaEntity getCuentaByIdcuenta() {
        return cuentaByIdcuenta;
    }

    public void setCuentaByIdcuenta(CuentaEntity cuentaByIdcuenta) {
        this.cuentaByIdcuenta = cuentaByIdcuenta;
    }

    public DivisaEntity getDivisaByDivisa() {
        return divisaByDivisa;
    }

    public void setDivisaByDivisa(DivisaEntity divisaByDivisa) {
        this.divisaByDivisa = divisaByDivisa;
    }

    public Operacion toDTO(){
        Operacion op = new Operacion();
        op.setIdoperacion(idoperación);
        op.setFecha(fecha);
        op.setCantidad(cantidad);
        op.setConcepto(concepto);
        op.setTipoOperacion(this.getTipoOperacionByTipopperacionid().toDTO());
        op.setDivisa(this.getDivisaByDivisa().toDTO());
        op.setCuentaOrigen(this.cuentaByIdcuenta.toDTO());
        op.setCuentaDestino(this.cuentaByIdcuenta2.toDTO());
        return op;
    }

}