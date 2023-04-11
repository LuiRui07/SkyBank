package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cuenta", schema = "skybank", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcuenta", nullable = false)
    private Integer idCuenta;
    @Basic
    @Column(name = "saldo", nullable = false, precision = 0)
    private Double saldo;
    @Basic
    @Column(name = "divisa", nullable = false, length = 10)
    private String divisa;
    @Basic
    @Column(name = "sospechosa", nullable = false)
    private Integer sospechosa;
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    private ClienteEntity clienteByIdCliente;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa", nullable = false)
    private EmpresaEntity empresaByIdEmpresa;
    @OneToMany(mappedBy = "cuentaByIdCuenta2")
    private List<OperacionEntity> operacionsByIdCuenta;
    @OneToMany(mappedBy = "cuentaByIdCuenta")
    private List<OperacionEntity> operacionsByIdCuenta_0;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public Integer getSospechosa() {
        return sospechosa;
    }

    public void setSospechosa(Integer sospechosa) {
        this.sospechosa = sospechosa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaEntity that = (CuentaEntity) o;
        return Objects.equals(idCuenta, that.idCuenta) && Objects.equals(saldo, that.saldo) && Objects.equals(divisa, that.divisa) && Objects.equals(sospechosa, that.sospechosa);
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

    public List<OperacionEntity> getOperacionsByIdCuenta() {
        return operacionsByIdCuenta;
    }

    public void setOperacionsByIdCuenta(List<OperacionEntity> operacionsByIdCuenta) {
        this.operacionsByIdCuenta = operacionsByIdCuenta;
    }

    public List<OperacionEntity> getOperacionsByIdCuenta_0() {
        return operacionsByIdCuenta_0;
    }

    public void setOperacionsByIdCuenta_0(List<OperacionEntity> operacionsByIdCuenta_0) {
        this.operacionsByIdCuenta_0 = operacionsByIdCuenta_0;
    }
}
