package com.example.book_store_security.controllers;

import com.example.book_store_security.Services.AuthenticationService;
import com.example.book_store_security.dtos.Permissions.AuthenticationDTO;
import com.example.book_store_security.dtos.Permissions.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UsersController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data)
    {
        if(authenticationService.loadUserByUsername(data.login())==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authenticationService.userLogin(data));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data)
    {
        if(authenticationService.loadUserByUsername(data.login())!=null) return ResponseEntity.badRequest().build();
        authenticationService.register(data);
        return ResponseEntity.ok().build();
    }
}
