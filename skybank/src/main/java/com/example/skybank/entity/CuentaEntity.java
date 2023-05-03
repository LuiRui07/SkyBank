package com.example.skybank.entity;

import com.example.skybank.dto.Cuenta;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cuenta", schema = "skybank", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcuenta", nullable = false)
    private int idcuenta;
    @Basic
    @Column(name = "saldo", nullable = false, precision = 0)
    private double saldo;
    @Basic
    @Column(name = "sospechosa", nullable = false)
    private int sospechosa;
    @Basic
    @Column(name = "activa", nullable = false)
    private int activa;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private ClienteEntity clienteByIdcliente;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    private EmpresaEntity empresaByIdempresa;
    @ManyToOne
    @JoinColumn(name = "divisa", referencedColumnName = "iddivisa", nullable = false)
    private DivisaEntity divisaByDivisa;
    @OneToMany(mappedBy = "cuentaByIdcuenta2")
    private List<OperacionEntity> operacionsByIdcuenta;
    @OneToMany(mappedBy = "cuentaByIdcuenta")
    private List<OperacionEntity> operacionsByIdcuenta_0;

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getSospechosa() {
        return sospechosa;
    }

    public void setSospechosa(int sospechosa) {
        this.sospechosa = sospechosa;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaEntity cuenta = (CuentaEntity) o;
        return idcuenta == cuenta.idcuenta && Double.compare(cuenta.saldo, saldo) == 0 && activa == cuenta.activa && Objects.equals(sospechosa, cuenta.sospechosa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcuenta, saldo, sospechosa, activa);
    }

    public ClienteEntity getClienteByIdcliente() {
        return clienteByIdcliente;
    }

    public void setClienteByIdcliente(ClienteEntity clienteByIdcliente) {
        this.clienteByIdcliente = clienteByIdcliente;
    }

    public EmpresaEntity getEmpresaByIdempresa() {
        return empresaByIdempresa;
    }

    public void setEmpresaByIdempresa(EmpresaEntity empresaByIdempresa) {
        this.empresaByIdempresa = empresaByIdempresa;
    }

    public DivisaEntity getDivisaByDivisa() {
        return divisaByDivisa;
    }

    public void setDivisaByDivisa(DivisaEntity divisaByDivisa) {
        this.divisaByDivisa = divisaByDivisa;
    }

    public List<OperacionEntity> getOperacionsByIdcuenta() {
        return operacionsByIdcuenta;
    }

    public void setOperacionsByIdcuenta(List<OperacionEntity> operacionsByIdcuenta) {
        this.operacionsByIdcuenta = operacionsByIdcuenta;
    }

    public List<OperacionEntity> getOperacionsByIdcuenta_0() {
        return operacionsByIdcuenta_0;
    }

    public void setOperacionsByIdcuenta_0(List<OperacionEntity> operacionsByIdcuenta_0) {
        this.operacionsByIdcuenta_0 = operacionsByIdcuenta_0;
    }


    public Cuenta toDTO(){
        Cuenta cuenta = new Cuenta();
        cuenta.setIdcuenta(this.idcuenta);
        cuenta.setSaldo(this.saldo);
        cuenta.setSospechosa(this.sospechosa);
        cuenta.setActiva(this.activa);
        cuenta.setDivisa(divisaByDivisa.toDTO());

        return cuenta;
    }
}
