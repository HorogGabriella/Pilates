package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.FoglalasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoglalasRepository extends JpaRepository<FoglalasEntity, Long> {

    //Foglalasok adott orahoz
    List<FoglalasEntity> findByOraId(Long oraid);

    List<FoglalasEntity> findByResztvevoNeve(String nev);
}
