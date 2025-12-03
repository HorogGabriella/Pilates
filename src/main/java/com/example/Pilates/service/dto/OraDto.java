package com.example.Pilates.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("teacher")
    private String oktato;
    @JsonProperty("classtype")
    private String oratipus;
    @JsonProperty("time")
    private LocalDateTime idopont;
    @JsonProperty("capacity")
    private int ferohely;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOktato() {
        return oktato;
    }

    public void setOktato(String oktato) {
        this.oktato = oktato;
    }

    public String getOratipus() {
        return oratipus;
    }

    public void setOratipus(String oratipus) {
        this.oratipus = oratipus;
    }

    public LocalDateTime getIdopont() {
        return idopont;
    }

    public void setIdopont(LocalDateTime idopont) {
        this.idopont = idopont;
    }

    public int getFerohely() {
        return ferohely;
    }

    public void setFerohely(int ferohely) {
        this.ferohely = ferohely;
    }
}
