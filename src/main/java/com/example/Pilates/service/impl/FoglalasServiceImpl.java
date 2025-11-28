package com.example.Pilates.service.impl;

import com.example.Pilates.service.FoglalasService;
import com.example.Pilates.service.dto.FoglalasDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoglalasServiceImpl implements FoglalasService {

    @Override
    public FoglalasDto createFoglalas(LocalDateTime idopont, FoglalasDto foglalasDto) {
        return null;
    }

    @Override
    public List<FoglalasDto> getBookingForClass(LocalDateTime idopont) {
        return null;
    }
}
