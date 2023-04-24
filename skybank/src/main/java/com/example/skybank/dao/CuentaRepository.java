package com.example.skybank.dao;

import com.example.skybank.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity,Integer> {
    @Query("select c from CuentaEntity c where c.clienteByIdCliente.idCliente = :idCliente")
    CuentaEntity getByCliente(@Param("idCliente") int idCliente);
}
