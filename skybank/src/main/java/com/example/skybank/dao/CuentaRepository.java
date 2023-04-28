package com.example.skybank.dao;

import com.example.skybank.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity,Integer> {
}
