package com.example.skybank.dao;

import com.example.skybank.entity.AutorizadoEntity;
import com.example.skybank.entity.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorizadoRepository extends JpaRepository<AutorizadoEntity, Integer> {

    @Query("SELECT a from AutorizadoEntity a where a.empresaByIdEmpresa.idEmpresa = :id")
    List<AutorizadoEntity> todosDeUnaEmpresa(@Param("id") Integer id);
}
