package com.example.skybank.dao;

import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity,Integer> {
    @Query("select o from OperacionEntity o where (o.cuentaByIdCuenta.idCuenta =: IdCuenta or o.cuentaByIdCuenta2.idCuenta =: IdCuenta)")
    Set<OperacionEntity> getOperaciones(@Param("idCuenta") int IdCuenta);
}
