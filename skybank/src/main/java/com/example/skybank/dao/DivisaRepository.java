/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.dao;

import com.example.skybank.entity.DivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisaRepository extends JpaRepository<DivisaEntity, Integer> {
}
