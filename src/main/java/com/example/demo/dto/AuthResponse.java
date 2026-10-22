package com.example.demo.dto;

public record AuthResponse(
        String token,
        String tokenType,
        String email,
        String role
) {}