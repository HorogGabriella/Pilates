package com.example.Pilates.service;

import com.example.Pilates.service.dto.OraDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OraService {
    List<OraDto> getAllClasses();
    //OraDto getClassByTime(LocalDateTime idopont);
    OraDto createClass(OraDto oraDto);
    OraDto updateClass(Long id, OraDto oradto);
    void deleteClass(Long id);
}
