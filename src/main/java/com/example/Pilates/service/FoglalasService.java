package com.example.Pilates.service;

import com.example.Pilates.service.dto.FoglalasDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FoglalasService {
    FoglalasDto createFoglalas(FoglalasDto foglalasDto);

    FoglalasDto bookClass(Long oraid, Long felhasznaloId);

    void cancelBooking(Long foglalasId);
}
