package com.example.Pilates.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "teacher")
    private String oktato;
    @Column(name = "classtype")
    private String oratipus;
    @Column(name = "time")
    private LocalDateTime idopont;
    @Column(name = "capacity")
    private int ferohely;

    public int getFoglalthely() {
        return foglalthely;
    }

    public void setFoglalthely(int foglalthely) {
        this.foglalthely = foglalthely;
    }

    @Column (name = "bookedspots")
    private int foglalthely;


    //kapcsolat a FoglalasEntityvel, ha az órát törlöm vagy módosítom, a hozzá tartozó foglalások is, ha egy foglalást kiveszek a listából akkor automatikusan törlődik az adatbáziból
    @OneToMany(mappedBy = "ora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoglalasEntity> foglalasok = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getOktato() {
        return oktato;
    }

    public String getOratipus() {
        return oratipus;
    }

    public LocalDateTime getIdopont() {
        return idopont;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOktato(String oktato) {
        this.oktato = oktato;
    }

    public void setOratipus(String oratipus) {
        this.oratipus = oratipus;
    }

    public void setIdopont(LocalDateTime idopont) {
        this.idopont = idopont;
    }

    public void setFerohely(int ferohely) {
        this.ferohely = ferohely;
    }

    public void setFoglalasok(List<FoglalasEntity> foglalasok) {
        this.foglalasok = foglalasok;
    }

    public int getFerohely() {
        return ferohely;
    }

    public List<FoglalasEntity> getFoglalasok() {
        return foglalasok;
    }

    //hány foglalás van az adott órára
    public int getBookedCount(){
        return foglalasok == null ?0: foglalasok.size();
    }

    //mennyi szabad hely maradt
    public int getAvailable(){
        return ferohely - getBookedCount();
    }

}
