package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final long JWT_EXPIRATION_MS = 60 * 60 * 1000; // 1 hour

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /* =========================
       TOKEN GENERATION
       ========================= */

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key)
                .compact();
    }

    /* =========================
       CLAIM EXTRACTION
       ========================= */

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    protected Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /* =========================
       TOKEN VALIDATION
       ========================= */

    protected boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /* =========================================================
       REQUIRED METHODS (DO NOT REMOVE – TEST CASE DEPENDS)
       ========================================================= */

    public Claims getClaims(String token) {
        return extractAllClaims(token);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
