package org.daylight.museumbackend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET = "ОченьДлинныйСекретныйКлючМинимум32Байта!!!"; // TODO move to application.properties
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (io.jsonwebtoken.security.SignatureException |
                 io.jsonwebtoken.ExpiredJwtException |
                 io.jsonwebtoken.MalformedJwtException e) {
            return null;
        }
    }

    public boolean validate(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username != null && username.equals(userDetails.getUsername());
    }
}
