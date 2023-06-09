/*
@author: Pablo García Platero
*/

package com.example.skybank.dao;

import com.example.skybank.entity.AsistenteEntity;
import com.example.skybank.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsistenteRepository extends JpaRepository<AsistenteEntity, Integer> {

    @Query("select a from AsistenteEntity a where (a.email= :email ) and a.password = :password")
    AsistenteEntity autenticar(@Param("email") String email, @Param("password") String contra);

}

