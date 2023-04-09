package com.example.skybank.dao;

import com.example.skybank.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Integer> {

    @Query("select e from EmpresaEntity e where e.nombre = :user and e.passwordEmpresa = :password")
    EmpresaEntity autenticar(@Param("user") String name,@Param("password") String contra);
}