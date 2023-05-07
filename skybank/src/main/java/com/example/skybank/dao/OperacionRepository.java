/*
    @autor: José Luis López Ruiz (15%), Rafael Ceballos Martinez (15%) y Luis Ruiz Nuñez (70%).
 */

package com.example.skybank.dao;
import com.example.skybank.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity,Integer> {

    @Query("select o from OperacionEntity o where o.cuentaByIdcuenta.idcuenta = :idCuenta and o.tipoOperacionByTipopperacionid.id = 1 and o.cantidad < 0 order by o.fecha desc")
    List<OperacionEntity> obtenerPagosNegativos(@Param("idCuenta") int idCuenta);
    @Query("select o from OperacionEntity o where o.cuentaByIdcuenta2.idcuenta = :idCuenta and o.tipoOperacionByTipopperacionid.id = 1 and o.cantidad > 0 order by o.fecha desc")
    List<OperacionEntity> obtenerPagosPositivos(@Param("idCuenta") int idCuenta);

    @Query("select o from OperacionEntity o where (o.cuentaByIdcuenta.idcuenta = :id or o.cuentaByIdcuenta2.idcuenta = :id )")
    List<OperacionEntity> findbyAccount(@Param("id") int id);

    @Query("select o from OperacionEntity o where (o.tipoOperacionByTipopperacionid.tipo = :tipo) and (o.cuentaByIdcuenta.idcuenta = :id or o.cuentaByIdcuenta2.idcuenta = :id )")
    List<OperacionEntity> filtrarPorTipo (@Param("tipo") String tipo, @Param("id") int id);

    @Query("select o from OperacionEntity o where (o.cantidad <= :maxC) and (o.cuentaByIdcuenta.idcuenta = " +
            ":id or o.cuentaByIdcuenta2.idcuenta = :id )")
    List<OperacionEntity> filtrarMax(@Param("maxC") Double maxC, @Param("id") int id);

    @Query("select o from OperacionEntity o where (:minC <= o.cantidad) and (o.cuentaByIdcuenta.idcuenta = " +
            ":id or o.cuentaByIdcuenta2.idcuenta = :id )")
    List<OperacionEntity> filtrarMin(@Param("minC") Double minC, @Param("id") int id);

    @Query("select o from OperacionEntity o where (:desde <= o.fecha) and (o.cuentaByIdcuenta.idcuenta = " +
            ":id or o.cuentaByIdcuenta2.idcuenta = :id ) ")
    List<OperacionEntity> filtrarDesde(@Param("desde") Date desde, @Param("id") int id);

    @Query("select o from OperacionEntity o where (o.fecha <= :hasta) and (o.cuentaByIdcuenta.idcuenta = " +
            ":id or o.cuentaByIdcuenta2.idcuenta = :id )")
    List<OperacionEntity> filtrarHasta(@Param("hasta") Date hasta, @Param("id") int id);


    @Query("select o from OperacionEntity o  where (o.cuentaByIdcuenta.idcuenta = :id or o.cuentaByIdcuenta2.idcuenta = :id ) order by o.fecha desc")
    List<OperacionEntity> ordenarPorFechaDesc(@Param("id") int id);

    @Query("select o from OperacionEntity o  where (o.cuentaByIdcuenta.idcuenta = :id or o.cuentaByIdcuenta2.idcuenta = :id ) order by o.fecha asc ")
    List<OperacionEntity> ordenarPorFechaAsc(@Param("id") int id);

    @Query("select o from OperacionEntity  o where (o.cuentaByIdcuenta.idcuenta = :id or o.cuentaByIdcuenta2.idcuenta = :id ) order by o.cantidad desc")
    List<OperacionEntity> ordenarPorCantidadDesc(@Param("id") int id);

    @Query("select o from OperacionEntity  o where (o.cuentaByIdcuenta.idcuenta = :id or o.cuentaByIdcuenta2.idcuenta = :id ) order by o.cantidad asc")
    List<OperacionEntity> ordenarPorCantidadAsc(@Param("id") int id);


    @Query("select distinct o from OperacionEntity o where DATEDIFF(:fechaAct, o.fecha)<30")
    List<OperacionEntity> obtenerOperacionesRecientes(@Param("fechaAct") Date actual);

    @Query("select distinct o from OperacionEntity o where o.cuentaByIdcuenta2.sospechosa = 1")
    List<OperacionEntity> obtenerOperacionesSospechosas();
}

