package com.example.Pilates.controller;

import com.example.Pilates.service.AuthenticationService;
import com.example.Pilates.service.dto.BejelentkezesDto;
import com.example.Pilates.service.dto.RegisztracioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/regisztracio")
    public ResponseEntity<String> regisztracio(@RequestBody RegisztracioDto regisztracioDto) {
        authService.regisztracio(regisztracioDto);
        return ResponseEntity.ok("Sikeres regisztráció");
    }

    @PostMapping("/bejelentkezes")
    public ResponseEntity bejelentkezes(@RequestBody BejelentkezesDto bejelentkezesDto) {
        String token = authService.bejelentkezes(bejelentkezesDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/user-email")
    public String getUserEmail(Authentication authentication) {
        return authentication.getName();
    }
}
