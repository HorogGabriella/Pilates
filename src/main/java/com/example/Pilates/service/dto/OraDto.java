package com.example.Pilates.service.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String oratipus;
    @NotBlank
    private String oktato;
    @NotBlank
    private int ferohely;
    @NotBlank
    private LocalDateTime idopont;
}
