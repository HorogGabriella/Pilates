package com.example.Pilates.service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FoglalasDto {
    private Long foglalasId;
    private Long oraId;
    private String userEmail;

    public Long getFoglalasId() {
        return foglalasId;
    }

    public void setFoglalasId(Long foglalasId) {
        this.foglalasId = foglalasId;
    }

    public void setOraId(Long oraId) {
        this.oraId = oraId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getOraId() {
        return oraId;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
