package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cuenta", schema = "skybank", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcuenta", nullable = false)
    private int idCuenta;
    @Basic
    @Column(name = "saldo", nullable = true, precision = 0)
    private double saldo;
    @Basic
    @Column(name = "divisa", nullable = false, length = 3)
    private String divisa;
    @Basic
    @Column(name = "sospechosa", nullable = false)
    private int sospechosa;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private ClienteEntity clienteByIdCliente;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    private EmpresaEntity empresaByIdEmpresa;
    @OneToMany(mappedBy = "cuentaByIdCuenta2")
    private Set<OperacionEntity> operacionsByIdCuenta;
    @OneToMany(mappedBy = "cuentaByIdCuenta")
    private Set<OperacionEntity> operacionsByIdCuenta_0;

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public int getSospechosa() {
        return sospechosa;
    }

    public void setSospechosa(int sospechosa) {
        this.sospechosa = sospechosa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaEntity that = (CuentaEntity) o;
        return idCuenta == that.idCuenta && Double.compare(that.saldo, saldo) == 0 && sospechosa == that.sospechosa && Objects.equals(divisa, that.divisa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCuenta, saldo, divisa, sospechosa);
    }

    public ClienteEntity getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(ClienteEntity clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }

    public EmpresaEntity getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(EmpresaEntity empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }

    public Set<OperacionEntity> getOperacionsByIdCuenta() {
        return operacionsByIdCuenta;
    }

    public void setOperacionsByIdCuenta(Set<OperacionEntity> operacionsByIdCuenta) {
        this.operacionsByIdCuenta = operacionsByIdCuenta;
    }

    public Set<OperacionEntity> getOperacionsByIdCuenta_0() {
        return operacionsByIdCuenta_0;
    }

    public void setOperacionsByIdCuenta_0(Set<OperacionEntity> operacionsByIdCuenta_0) {
        this.operacionsByIdCuenta_0 = operacionsByIdCuenta_0;
    }
}