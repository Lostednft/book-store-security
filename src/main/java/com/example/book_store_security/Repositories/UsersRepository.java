package com.example.book_store_security.Repositories;

import com.example.book_store_security.domains.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {

    Users findByLogin(String login);
}
