/*
    @autor: José Luis López Ruiz(50%) y Rafael Ceballos Martinez(50%).
 */

package com.example.skybank.dao;

import com.example.skybank.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Integer> {

    @Query("select distinct e from EmpresaEntity e where e.nombre = :user and e.passwordempresa = :password")
    EmpresaEntity autenticar(@Param("user") String name,@Param("password") String contra);

    @Query("select e from EmpresaEntity e where e.verificado = 0")
    List<EmpresaEntity> getPendientesVerificar();

}