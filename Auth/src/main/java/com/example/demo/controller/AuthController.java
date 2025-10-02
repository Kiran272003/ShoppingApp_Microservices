package com.example.demo.controller;


import com.example.demo.Security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    // Temporary in-memory user storage for demo
    private final Map<String, String> users = new HashMap<>(); // username->encoded password
    private final Map<String, String> roles = new HashMap<>(); // username->role

    public AuthController(PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String role) {
        if (users.containsKey(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User exists");
        }
        users.put(username, encoder.encode(password));
        roles.put(username, role.startsWith("ROLE_") ? role : "ROLE_" + role);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username,
                                   @RequestParam String password) {
        if (!users.containsKey(username) || !encoder.matches(password, users.get(username))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid creds");
        }
        String token = jwtUtil.generateToken(username, List.of(roles.get(username)));
        return ResponseEntity.ok(Map.of("token", token));
    }
}
