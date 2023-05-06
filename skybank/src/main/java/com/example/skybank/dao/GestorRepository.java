package com.example.skybank.dao;

import com.example.skybank.entity.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GestorRepository extends JpaRepository<Gestor,Integer> {
    @Query("select g from Gestor g where g.dni= :user and g.password = :password")
    Gestor autenticar(@Param("user") String name, @Param("password") String contra);
}
