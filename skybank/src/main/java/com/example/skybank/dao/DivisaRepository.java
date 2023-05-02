/*
@author: Luis Ruiz Nuñez
*/

package com.example.skybank.dao;

import com.example.skybank.entity.DivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DivisaRepository extends JpaRepository<DivisaEntity,Integer> {
    @Query("select d from DivisaEntity d where d.nombre =: nombre")
    Double getValorbyName(@Param("nombre") String nombre);
}
