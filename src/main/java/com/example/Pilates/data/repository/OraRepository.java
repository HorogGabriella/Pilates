package com.example.Pilates.data.repository;

import com.example.Pilates.data.entity.OraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OraRepository extends JpaRepository<OraEntity, Long> {
    //List<OraEntity>getByTime(LocalDateTime idopont);

    OraEntity getById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE From OraEntity c where (c.id) = (:id)")
    void deleteClass(Long id);
}
