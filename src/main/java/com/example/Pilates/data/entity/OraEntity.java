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
    private Long id;
    private String oktato;
    private String oratipus;
    private LocalDateTime idopont;
    private int ferohely;

    //kapcsolat a FoglalasEntityvel, ha az órát törlöm vagy módosítom, a hozzá tartozó foglalások is, ha egy foglalást kiveszek a listából akkor automatikusan törlődik az adatbáziból
    @OneToMany(mappedBy = "ora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoglalasEntity> foglalasok = new ArrayList<>();


    //hány foglalás van az adott órára
    public int getBookedCount(){
        return foglalasok == null ?0: foglalasok.size();
    }

    //mennyi szabad hely maradt
    public int getAvailable(){
        return ferohely - getBookedCount();
    }

}
