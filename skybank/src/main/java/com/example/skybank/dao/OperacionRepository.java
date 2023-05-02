package com.example.skybank.dao;

import com.example.skybank.entity.OperacionEntity;
import com.example.skybank.ui.FiltroOperaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;

import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity,Integer> {
    @Query("select o from OperacionEntity o where (o.cuentaByIdcuenta.idcuenta = :id or o.cuentaByIdcuenta2.idcuenta = :id ) order by o.fecha desc ")
    List<OperacionEntity> findbyAccount(@Param("id") int id);

    @Query("select o from OperacionEntity o where o.tipoOperacionByTipopperacionid.id = :tipo")
    List<OperacionEntity> filtrarPorTipo (@Param("tipo") int tipo);
}
