package com.example.book_store_security.Repositories;

import com.example.book_store_security.domains.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {

    Author findByName(String name);
}
