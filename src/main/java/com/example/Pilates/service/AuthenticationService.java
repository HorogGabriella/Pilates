package com.example.Pilates.service;

import com.example.Pilates.service.dto.BejelentkezesDto;
import com.example.Pilates.service.dto.RegisztracioDto;

public interface AuthenticationService {
    public void regisztracio(RegisztracioDto regisztracioDto);
    public String bejelentkezes(BejelentkezesDto bejelentkezesDto);
}
