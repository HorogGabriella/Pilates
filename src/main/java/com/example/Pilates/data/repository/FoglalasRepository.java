package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.FoglalasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FoglalasRepository extends JpaRepository<FoglalasEntity, Long> {

    FoglalasEntity getByFoglaloNeve(String nev);
}
