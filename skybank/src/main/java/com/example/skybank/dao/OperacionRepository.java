package com.example.skybank.dao;

import com.example.skybank.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity,Integer> {

    @Query("select o from OperacionEntity o where o.cuentaByIdcuenta.idcuenta = :idCuenta and o.tipoOperacionByTipopperacionid.id = 1 and o.cantidad < 0 order by o.fecha desc")
    List<OperacionEntity> obtenerPagosNegativos(@Param("idCuenta") int idCuenta);

    @Query("select o from OperacionEntity o where o.cuentaByIdcuenta2.idcuenta = :idCuenta and o.tipoOperacionByTipopperacionid.id = 1 and o.cantidad > 0 order by o.fecha desc")
    List<OperacionEntity> obtenerPagosPositivos(@Param("idCuenta") int idCuenta);
}
