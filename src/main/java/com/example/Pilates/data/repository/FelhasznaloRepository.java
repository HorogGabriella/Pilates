package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FelhasznaloRepository extends JpaRepository <FelhasznaloEntity, Long> {
    FelhasznaloEntity findByEmail(String email);
}
