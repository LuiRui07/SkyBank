package com.example.skybank.dao;

import com.example.skybank.entity.GestorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestorRepository extends JpaRepository<GestorEntity,Integer> {
}
