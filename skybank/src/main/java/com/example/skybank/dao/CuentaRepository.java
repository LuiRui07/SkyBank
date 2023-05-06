/*
@author: Luis Ruiz Nuñez
*/

package com.example.skybank.dao;

import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.DivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity,Integer> {

    @Query("select c from CuentaEntity c where (c.divisaByDivisa.iddivisa = :divisa and c.idcuenta <>:cuenta)")
    List<CuentaEntity> findByDivisa(@Param("divisa")int divisa,@Param("cuenta") int cuenta);

    @Query("select c from CuentaEntity c where c.clienteByIdcliente.idcliente = :cliente")
    List<CuentaEntity> findByCliente(@Param("cliente")int cliente);

    @Query("select c from CuentaEntity c where c.empresaByIdempresa.idempresa = :empresa")
    List<CuentaEntity> findByEmpresa(@Param("empresa")int empresa);
}
