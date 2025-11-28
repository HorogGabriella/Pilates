package com.example.Pilates.service;

import com.example.Pilates.service.dto.FoglalasDto;

import java.util.List;

public interface FoglalasService {
    FoglalasDto createFoglalas(Long classId, FoglalasDto foglalasDto);
    List<FoglalasDto> getBookingForClass(Long classId);
}
