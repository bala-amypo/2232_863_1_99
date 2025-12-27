package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // Assume user already validated
        String token = jwtUtil.generateToken(
                request.getEmail(),
                1L,
                Set.of("USER")
        );

        return new AuthResponse(token);
    }
}
