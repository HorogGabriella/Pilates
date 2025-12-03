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
    private String resztvevoNeve;

    public Long getFoglalasId() {
        return foglalasId;
    }

    public Long getOraId() {
        return oraId;
    }

    public String getResztvevoNeve() {
        return resztvevoNeve;
    }
}
