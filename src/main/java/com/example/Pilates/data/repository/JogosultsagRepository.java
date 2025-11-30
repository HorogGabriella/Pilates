package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.JogosultsagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogosultsagRepository extends JpaRepository<JogosultsagEntity, Long> {

    JogosultsagEntity findByJog(String jog);
}
