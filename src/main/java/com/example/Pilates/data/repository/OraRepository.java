package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.OraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OraRepository extends JpaRepository<OraEntity,Long> {
    List<OraEntity> findByOraId(Long oraid);

}
