package com.example.Pilates.service.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class BejelentkezesDto {
    private String email;
    private String jelszo;

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BejelentkezesDto that = (BejelentkezesDto) o;
        return Objects.equals(email, that.email) && Objects.equals(jelszo, that.jelszo);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, jelszo);
    }

    public String getJelszo() {
        return jelszo;
    }
}
