package com.example.Pilates.service.impl;

import com.example.Pilates.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenServiceImpl implements TokenService {
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        userDetails.getAuthorities().forEach(a -> claims.put(a.getAuthority(), a));

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(new Date(System.currentTimeMillis()+150000))
                .signWith(getKey())
                .compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        Date d = extractClaim(token, Claims::getExpiration);
        return d.before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode("oitguijopkfgrthujgftzhuzzziopkfghujfghjfzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
