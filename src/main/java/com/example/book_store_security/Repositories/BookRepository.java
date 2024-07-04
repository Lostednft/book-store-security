package com.example.book_store_security.Repositories;

import com.example.book_store_security.domains.Author;
import com.example.book_store_security.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BookRepository extends JpaRepository<Book, String> {

    Book findByName(String name);
}
