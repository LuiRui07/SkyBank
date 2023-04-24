package com.example.skybank.dao;

import com.example.skybank.entity.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<SocioEntity,Integer> {

    @Query("SELECT s from SocioEntity s where s.empresaByIdEmpresa.idEmpresa = :id")
    List<SocioEntity> todosDeUnaEmpresa(@Param("id") Integer id);
}
