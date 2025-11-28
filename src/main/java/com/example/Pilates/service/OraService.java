package com.example.Pilates.service;

import com.example.Pilates.service.dto.OraDto;

import java.util.List;

public interface OraService {
    List<OraDto> getAllClasses();
    OraDto getClassById(Long id);
    OraDto saveClass(OraDto classDto);
    void deleteClass(Long id);
}
