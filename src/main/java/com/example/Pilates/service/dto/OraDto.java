package com.example.Pilates.service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class OraDto {
    private Long id;
    private String oktato;
    private String oratipus;
    private LocalDateTime idopont;
    private int ferohely;
}
