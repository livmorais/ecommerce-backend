package com.example.ecommerce.domain.user;

public record RegisterDTO(String login, String email, String password, UserRole role) {
}
