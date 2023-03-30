package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Cuenta", schema = "skybank", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCuenta", nullable = false)
    private Integer idCuenta;
    @Basic
    @Column(name = "Saldo", nullable = false, precision = 0)
    private Double saldo;
    @Basic
    @Column(name = "Divisa", nullable = false, length = 10)
    private String divisa;
    @Basic
    @Column(name = "Sospechosa", nullable = false)
    private Integer sospechosa;
    @ManyToOne
    @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente", nullable = false)
    private ClienteEntity clienteByClienteIdCliente;
    @ManyToOne
    @JoinColumn(name = "Empresa_idEmpresa", referencedColumnName = "idEmpresa", nullable = false)
    private EmpresaEntity empresaByEmpresaIdEmpresa;
    @OneToMany(mappedBy = "cuentaByCuentaIdCuenta")
    private Collection<OperacionEntity> operacionsByIdCuenta;
    @OneToMany(mappedBy = "cuentaByCuentaIdCuenta1")
    private Collection<OperacionEntity> operacionsByIdCuenta_0;

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

    public ClienteEntity getClienteByClienteIdCliente() {
        return clienteByClienteIdCliente;
    }

    public void setClienteByClienteIdCliente(ClienteEntity clienteByClienteIdCliente) {
        this.clienteByClienteIdCliente = clienteByClienteIdCliente;
    }

    public EmpresaEntity getEmpresaByEmpresaIdEmpresa() {
        return empresaByEmpresaIdEmpresa;
    }

    public void setEmpresaByEmpresaIdEmpresa(EmpresaEntity empresaByEmpresaIdEmpresa) {
        this.empresaByEmpresaIdEmpresa = empresaByEmpresaIdEmpresa;
    }

    public Collection<OperacionEntity> getOperacionsByIdCuenta() {
        return operacionsByIdCuenta;
    }

    public void setOperacionsByIdCuenta(Collection<OperacionEntity> operacionsByIdCuenta) {
        this.operacionsByIdCuenta = operacionsByIdCuenta;
    }

    public Collection<OperacionEntity> getOperacionsByIdCuenta_0() {
        return operacionsByIdCuenta_0;
    }

    public void setOperacionsByIdCuenta_0(Collection<OperacionEntity> operacionsByIdCuenta_0) {
        this.operacionsByIdCuenta_0 = operacionsByIdCuenta_0;
    }
}
