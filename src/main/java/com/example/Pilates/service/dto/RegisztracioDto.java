package com.example.Pilates.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisztracioDto {
    @NotBlank(message = "Ne legyen üres")
    @Size(min = 3, max = 100)
    private String felhasznaloNev;
    private String nev;
    @NotBlank
    @Size(min = 8, max = 200)
    private String jelszo;
}
