package com.example.demo.Security;


public final class JwtConstants {
    public static final String SECRET = "replace-with-a-very-strong-secret";
    public static final long EXPIRATION_MS = 1000 * 60 * 60 * 8; // 8h
    public static final String ISSUER = "auth-service";
}
