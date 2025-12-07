package com.example.Pilates.service;

import com.example.Pilates.service.dto.FoglalasDto;

public interface FoglalasService {
    FoglalasDto createFoglalas(Long oraId);
    void cancelFoglalas(Long foglalasId);
    int getOraFoglalasSzama(Long oraId);
}
