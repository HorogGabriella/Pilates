package com.example.Pilates.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
}
