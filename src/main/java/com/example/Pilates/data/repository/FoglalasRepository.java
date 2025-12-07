package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import com.example.Pilates.data.entity.FoglalasEntity;
import com.example.Pilates.data.entity.OraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FoglalasRepository extends JpaRepository<FoglalasEntity, Long> {
    boolean existsByFelhasznaloAndOra(FelhasznaloEntity felhasznalo, OraEntity ora);
}
