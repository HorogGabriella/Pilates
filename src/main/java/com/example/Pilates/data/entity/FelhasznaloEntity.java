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
    private Long id;
    @Column(name = "name", nullable = false)
    private String nev;
    @Column(name = "username")
    private String felhasznaloNev;
    @Column(name = "password")
    private String jelszo;

    @ManyToOne
    private JogosultsagEntity jogosultsag;


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
        return felhasznaloNev;
    }
}
