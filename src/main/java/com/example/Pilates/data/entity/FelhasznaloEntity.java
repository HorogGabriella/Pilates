package com.example.Pilates.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FelhasznaloEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String nev;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "password")
    private String jelszo;

    @ManyToOne
    private JogosultsagEntity jogosultsag;

    @OneToMany(mappedBy = "felhasznalo")
    private List<FoglalasEntity> foglalasok;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(jogosultsag);
    }

    @Override
    public String getPassword() {
        return jelszo;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public void setJogosultsag(JogosultsagEntity jogosultsag) {
        this.jogosultsag = jogosultsag;
    }

    public void setFoglalasok(List<FoglalasEntity> foglalasok) {
        this.foglalasok = foglalasok;
    }
}
