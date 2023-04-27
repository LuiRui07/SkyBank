package com.example.skybank.dao;

import com.example.skybank.entity.TipoOperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoOperacionRepository extends JpaRepository<TipoOperacionEntity,Integer> {
}
