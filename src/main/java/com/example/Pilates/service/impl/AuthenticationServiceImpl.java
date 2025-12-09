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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;



@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final FelhasznaloRepository repo;
    private final JogosultsagRepository jogrepo;
    private final FelhasznaloMapper mapper;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthenticationServiceImpl(
            PasswordEncoder passwordEncoder,
            FelhasznaloRepository repo,
            JogosultsagRepository jogrepo,
            FelhasznaloMapper mapper,
            AuthenticationManager authManager,
            TokenService tokenService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
        this.jogrepo = jogrepo;
        this.mapper = mapper;
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @Override
    public void regisztracio(RegisztracioDto regisztracioDto) {

        if (repo.findByEmail(regisztracioDto.getEmail()) != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ezzel az e-mail címmel már létezik fiók"
            );
        }

        if (regisztracioDto.getJelszo() == null ||
                regisztracioDto.getJelszo().length() < 8) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A jelszónak legalább 8 karakter hosszúnak kell lennie"
            );
        }

        FelhasznaloEntity e = mapper.regFelh(regisztracioDto);
        e.setJelszo(passwordEncoder.encode(regisztracioDto.getJelszo()));

        JogosultsagEntity jog = jogrepo.findByJog("FELHASZNALO");
        if (jog == null) {
            jog = new JogosultsagEntity();
            jog.setJog("FELHASZNALO");
            jog = jogrepo.save(jog);
        }

        e.setJogosultsag(jog);
        repo.save(e);
    }


    @Override
    public String bejelentkezes(BejelentkezesDto dto) {

        FelhasznaloEntity felhasznalo = repo.findByEmail(dto.getEmail());

        if (felhasznalo == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Ezzel az e-mail címmel még nincs regisztráció"
            );
        }

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getJelszo()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            return tokenService.generateToken(felhasznalo);

        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Hibás jelszó"
            );
        }
    }


    @Override
    public Long getFelhasznaloIdByEmail(String email) {
        FelhasznaloEntity felhasznalo = repo.findByEmail(email);
        return felhasznalo.getId();
    }
}
