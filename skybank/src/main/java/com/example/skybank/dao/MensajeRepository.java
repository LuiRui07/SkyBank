package com.example.skybank.dao;

import com.example.skybank.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeEntity,Integer> {
    @Query("select m from MensajeEntity m where m.conversacionByIdconversacion.idconversacion =:idChat order by  m.hora")
    public List<MensajeEntity> listaMensajes(@Param("idChat") Integer idChat);
}
