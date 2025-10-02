package com.example.demo.Security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.*;

public class JwtUtil {
    private final Key key = Keys.hmacShaKeyFor(JwtConstants.SECRET.getBytes());

    public String generateToken(String username, List<String> roles) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuer(JwtConstants.ISSUER)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JwtConstants.EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
