package com.example.Pilates.service;

import com.example.Pilates.service.dto.FoglalasDto;

public interface FoglalasService {
    FoglalasDto createFoglalas(FoglalasDto foglalasDto);
    void cancelFoglalas(Long foglalasId);
    int getOraFoglalasSzama(Long oraId);
}
