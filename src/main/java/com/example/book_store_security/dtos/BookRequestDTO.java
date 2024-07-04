package com.example.book_store_security.dtos;

import java.util.List;

public record BookRequestDTO(String name, Integer price, boolean bookAvailable, List<String> authorsId) {
}
