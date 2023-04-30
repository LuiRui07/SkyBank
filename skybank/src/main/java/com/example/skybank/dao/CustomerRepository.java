/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.dao;

import com.example.skybank.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<ClienteEntity, Integer> {

}
