package com.example.book_store_security.dtos;

import com.example.book_store_security.domains.Author;

import java.util.List;

public record BookRequestUpdateDTO(String id, String name, Integer price, boolean bookAvailable, List<String> authorsId) {

}
