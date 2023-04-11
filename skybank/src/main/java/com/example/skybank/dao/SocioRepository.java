package com.example.skybank.dao;

import com.example.skybank.entity.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<SocioEntity,Integer> {
}
