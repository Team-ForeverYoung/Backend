package com.java.backend.domain.example.dto;

public class LoginResponseDto {
    private String token; // JWT 등

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
