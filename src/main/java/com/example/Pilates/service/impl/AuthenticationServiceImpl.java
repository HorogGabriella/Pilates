package com.example.Pilates.service.impl;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import com.example.Pilates.data.entity.JogosultsagEntity;
import com.example.Pilates.data.repository.FelhasznaloRepository;
import com.example.Pilates.data.repository.JogosultsagRepository;
import com.example.Pilates.service.AuthenticationService;
import com.example.Pilates.service.TokenService;
import com.example.Pilates.service.dto.BejelentkezesDto;
import com.example.Pilates.service.dto.RegisztracioDto;
import com.example.Pilates.service.mapper.FelhasznaloMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    PasswordEncoder passwordEncoder;
    FelhasznaloRepository repo;
    JogosultsagRepository jogrepo;
    FelhasznaloMapper mapper;
    AuthenticationManager authManager;
    TokenService tokenService;

    @Override
    public void regisztracio(RegisztracioDto regisztracioDto) {
        FelhasznaloEntity e = mapper.regFelh(regisztracioDto);
        e.setJelszo(passwordEncoder.encode(e.getPassword()));

        JogosultsagEntity jog = jogrepo.findByJog("FELHASZNALO");
        if(jog != null){
            e.setJogosultsag(jog);
        } else {
            jog = new JogosultsagEntity();
            jog.setJog("FELHASZNALO");
            jog = jogrepo.save(jog);

            e.setJogosultsag(jog);
        }

        repo.save(e);
    }

    @Override
    public String bejelentkezes(BejelentkezesDto bejelentkezesDto) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        bejelentkezesDto.getEmail(),
                        bejelentkezesDto.getJelszo()
                )
        );
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        FelhasznaloEntity f =
                repo.findByEmail(bejelentkezesDto.getEmail());
        return tokenService.generateToken(f);

    }
}
