package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FelhasznaloRepository extends JpaRepository <FelhasznaloEntity, Long> {
    FelhasznaloEntity findByFelhasznaloNev(String felhasznaloNev);
}
