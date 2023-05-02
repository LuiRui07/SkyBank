package com.example.skybank.dao;

import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.GestorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GestorRepository extends JpaRepository<GestorEntity,Integer> {
    @Query("select g from GestorEntity g where g.dni= :user and g.password = :password")
    GestorEntity autenticar(@Param("user") String name, @Param("password") String contra);
}
