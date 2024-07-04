package com.example.book_store_security.controllers;

import com.example.book_store_security.Services.AuthorServices;
import com.example.book_store_security.domains.Author;
import com.example.book_store_security.dtos.AuthorRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorServices authorServices;

    public AuthorController(AuthorServices authorServices) {
        this.authorServices = authorServices;
    }

    @PostMapping
    public ResponseEntity saveAuthor(@RequestBody AuthorRequestDTO data){

        return ResponseEntity.ok(authorServices.saveAuthor(data));
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAllAuthors(){
        return ResponseEntity.ok(authorServices.getAllAuthor());
    }

    @GetMapping("/{authorName}")
    public ResponseEntity getBookByName(@PathVariable String authorName){

        if(authorServices.getAuthorByName(authorName) == null) return ResponseEntity.badRequest().build();

        authorServices.getAuthorByName(authorName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{authorDeleted}")
    public ResponseEntity deleteAuthorByName(@PathVariable String authorDeleted){
        authorServices.deleteAuthorByName(authorDeleted);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity updateAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){
        if(authorServices.updateAuthorRequested(authorRequestDTO) == null) return ResponseEntity.badRequest().build();
        authorServices.updateAuthorRequested(authorRequestDTO);
        return ResponseEntity.ok("Author Update Successfully!");
    }
}
