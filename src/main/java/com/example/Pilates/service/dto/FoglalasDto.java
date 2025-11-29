package com.example.Pilates.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoglalasDto {
    private Long foglalasId;
    private Long oraId;
    private String resztvevoNeve;
}
