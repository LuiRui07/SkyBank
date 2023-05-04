package com.example.skybank.dao;

import com.example.skybank.entity.AsistenteEntity;
import com.example.skybank.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsistenteRepository extends JpaRepository<AsistenteEntity, Integer> {

    @Query("select a from AsistenteEntity a where (a.idasistente= :user ) and a.idasistente = :password")
    AsistenteEntity autenticar(@Param("user") String name, @Param("password") String contra);

}

