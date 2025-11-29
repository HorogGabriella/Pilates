package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.OraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OraRepository extends JpaRepository<OraEntity,Long> {
    List<OraEntity>getByTime(LocalDateTime oraid);

    OraEntity getByOraid(Long id);

    @Modifying
    @Transactional
    @Query("DELETE From OraEntity c where lower(c.id) = lower(:id)")
    void deleteClass(Long id);
}
