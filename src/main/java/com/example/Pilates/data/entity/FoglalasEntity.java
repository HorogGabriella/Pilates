package com.example.Pilates.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoglalasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String resztvevoNeve;

    @ManyToOne
    @JoinColumn(name = "class_session_id")
    private OraEntity ora;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private FelhasznaloEntity felhasznalo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResztvevoNeve() {
        return resztvevoNeve;
    }

    public void setResztvevoNeve(String resztvevoNeve) {
        this.resztvevoNeve = resztvevoNeve;
    }

    public OraEntity getOra() {
        return ora;
    }

    public void setOra(OraEntity ora) {
        this.ora = ora;
    }

    public FelhasznaloEntity getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(FelhasznaloEntity felhasznalo) {
        this.felhasznalo = felhasznalo;
    }
}
