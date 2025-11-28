package com.example.Pilates.service;

import com.example.Pilates.service.dto.FoglalasDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FoglalasService {
    FoglalasDto createFoglalas(LocalDateTime idopont, FoglalasDto foglalasDto);
    List<FoglalasDto> getBookingForClass(LocalDateTime idopont);
}
