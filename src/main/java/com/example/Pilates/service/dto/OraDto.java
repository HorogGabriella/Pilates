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
public class OraDto {
    private String oratipus;
    private String oktato;
    private int ferohely;
    private LocalDateTime idopont;
}
