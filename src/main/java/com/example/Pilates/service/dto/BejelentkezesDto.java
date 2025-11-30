package com.example.Pilates.service.dto;

import lombok.Data;

@Data
public class BejelentkezesDto {
    private String email;
    private String jelszo;

    public String getEmail() {
        return email;
    }

    public String getJelszo() {
        return jelszo;
    }
}
