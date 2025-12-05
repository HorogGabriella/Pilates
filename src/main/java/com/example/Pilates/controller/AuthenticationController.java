package com.example.Pilates.controller;

import com.example.Pilates.service.AuthenticationService;
import com.example.Pilates.service.dto.BejelentkezesDto;
import com.example.Pilates.service.dto.RegisztracioDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/regisztracio")
    public void regisztracio(@RequestBody RegisztracioDto regisztracioDto) {
        authService.regisztracio(regisztracioDto);
    }

    @PostMapping("/bejelentkezes")
    public String bejelentkezes(@RequestBody BejelentkezesDto bejelentkezesDto) {
        return authService.bejelentkezes(bejelentkezesDto);
    }
}
