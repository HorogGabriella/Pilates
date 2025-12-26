package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import com.example.Pilates.data.entity.FoglalasEntity;
import com.example.Pilates.data.entity.OraEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FoglalasRepository extends JpaRepository<FoglalasEntity, Long> {
    boolean existsByFelhasznaloAndOra(FelhasznaloEntity felhasznalo, OraEntity ora);
    List<FoglalasEntity> findByFelhasznaloEmail(String email);

    List<FoglalasEntity> findByOraId(Long oraId);

    @Modifying
    @Transactional
    @Query("DELETE FROM FoglalasEntity f WHERE f.ora.id = :oraId")
    void deleteByOraId(@Param("oraId") Long oraId);

}
