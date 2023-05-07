/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.dao;

import com.example.skybank.entity.AutorizadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorizadoRepository extends JpaRepository<AutorizadoEntity, Integer> {

    @Query("select a from AutorizadoEntity a where a.nif = :nif and a.password = :password")
    AutorizadoEntity autenticar(@Param("nif") String nif, @Param("password") String password);

    @Query("SELECT a from AutorizadoEntity a where a.empresaByIdempresa.idempresa = :id")
    List<AutorizadoEntity> todosDeUnaEmpresa(@Param("id") Integer id);

    //Rafael Ceballos
    @Query ("select a from AutorizadoEntity a where a.solicituddesbloqueo = 1")
    List<AutorizadoEntity> solicitudesDesbloqueo();
}
