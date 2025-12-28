package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mySecretKeyForJWTTokenGenerationThatIsLongEnough";

    private static final long EXPIRATION_TIME = 86_400_000L; // 24 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // FIXED: email is now an explicit claim
    public String generateToken(String email, Long userId, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)                 // subject
                .claim("email", email)             // ✅ REQUIRED for test
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return extractClaims(token);
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String extractEmail(String token) {
        Claims claims = extractClaims(token);
        return claims != null ? claims.get("email", String.class) : null;
    }

    public Long extractUserId(String token) {
        Claims claims = extractClaims(token);
        return claims != null ? claims.get("userId", Long.class) : null;
    }

    @SuppressWarnings("unchecked")
    public Set<String> extractRoles(String token) {
        Set<String> rolesSet = new HashSet<>();
        Claims claims = extractClaims(token);

        if (claims != null) {
            Object rolesObj = claims.get("roles");
            if (rolesObj instanceof List<?>) {
                for (Object role : (List<?>) rolesObj) {
                    rolesSet.add(role.toString());
                }
            }
        }
        return rolesSet;
    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractClaims(token);
        return claims != null && claims.getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {
        Claims claims = extractClaims(token);
        return claims != null && !isTokenExpired(token);
    }

    public boolean validateToken(String token, String email) {
        String tokenEmail = extractEmail(token);
        return tokenEmail != null
                && tokenEmail.equals(email)
                && !isTokenExpired(token);
    }
}
