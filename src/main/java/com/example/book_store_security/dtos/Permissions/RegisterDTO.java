package com.example.book_store_security.dtos.Permissions;

import com.example.book_store_security.domains.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
