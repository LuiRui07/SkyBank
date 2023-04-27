package com.example.skybank.dao;

import com.example.skybank.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacionRepository extends JpaRepository<OperacionEntity,Integer> {
}
