package com.example.book_store_security.controllers;


import com.example.book_store_security.Services.BookServices;
import com.example.book_store_security.domains.Book;
import com.example.book_store_security.dtos.BookRequestDTO;
import com.example.book_store_security.dtos.BookRequestUpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@AllArgsConstructor
public class BookController {

    private final BookServices bookServices;

    @PostMapping
    public ResponseEntity saveBook(@RequestBody BookRequestDTO data){
        bookServices.saveBook(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBook(){
        return ResponseEntity.ok(bookServices.getAllBooks());
    }
    @GetMapping("/{nameBook}")
    public ResponseEntity findBookByName(@PathVariable String nameBook){

        if(bookServices.getBookByName(nameBook)==null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(bookServices.getBookByName(nameBook));
    }

    @DeleteMapping("/{idBook}")
    public ResponseEntity deleteBookByName(@PathVariable String idBook)
    {
        bookServices.deleteBookByName(idBook);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody BookRequestUpdateDTO idBook){
        bookServices.updateBook(idBook);
        return ResponseEntity.ok("Book Updated Successfully!");
    }
}
