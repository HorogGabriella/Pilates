package com.example.Pilates.service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FoglalasDto {
    private Long foglalasId;
    private Long oraId;
    private String oratipus;
    private String oktato;
    private LocalDateTime idopont;

    public Long getFoglalasId() {
        return foglalasId;
    }

    public void setFoglalasId(Long foglalasId) {
        this.foglalasId = foglalasId;
    }

    public Long getOraId() {
        return oraId;
    }

    public void setOraId(Long oraId) {
        this.oraId = oraId;
    }

    public String getOratipus() {
        return oratipus;
    }

    public void setOratipus(String oratipus) {
        this.oratipus = oratipus;
    }

    public String getOktato() {
        return oktato;
    }

    public void setOktato(String oktato) {
        this.oktato = oktato;
    }

    public LocalDateTime getIdopont() {
        return idopont;
    }

    public void setIdopont(LocalDateTime idopont) {
        this.idopont = idopont;
    }
}