/*
@author: Luis Ruiz Nuñez
*/
package com.example.skybank.dao;

import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface CustomerRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("select c from ClienteEntity c where (c.dni= :user or c.email= : user) and c.password = :password")
    ClienteEntity autenticar(@Param("user") String name, @Param("password") String contra);
}
