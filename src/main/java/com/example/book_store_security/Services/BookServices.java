package com.example.book_store_security.Services;

import com.example.book_store_security.Repositories.AuthorRepository;
import com.example.book_store_security.Repositories.BookRepository;
import com.example.book_store_security.domains.Book;
import com.example.book_store_security.dtos.BookRequestDTO;
import com.example.book_store_security.dtos.BookRequestUpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServices {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Transactional
    public Book saveBook(BookRequestDTO data) {

        if (bookRepository.findByName(data.name()) != null)  throw new RuntimeException("Esse nome ja esta registrado no sistema.");

        var book = new Book();
        book.setName(data.name());
        book.setPrice(data.price());
        book.setBookAvailable(data.bookAvailable());
        book.setAuthor(new HashSet<>(authorRepository.findAllById(data.authorsId())));

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){

        return bookRepository.findAll();
    }

    public Book getBookByName(String idBook){

        return bookRepository.findByName(idBook);
    }

    @Transactional
    public void deleteBookByName(String idBook){

        Book bookFounded = bookRepository.findById(idBook).orElseThrow(() -> new RuntimeException("Livro não encontrado no sistema"));
        if (bookFounded != null) bookRepository.delete(bookFounded);
    }

    @Transactional
    public Book updateBook(BookRequestUpdateDTO book){
        Book bookAtualizado = bookRepository.findById(book.id()).orElseThrow(() -> new RuntimeException("Livro não encontrato no sistema"));
        bookAtualizado.setBookAvailable(book.bookAvailable());
        bookAtualizado.setName(book.name());
        bookAtualizado.setPrice(book.price());
        bookAtualizado.setAuthor(new HashSet<>(authorRepository.findAllById(book.authorsId())));
        return bookRepository.save(bookAtualizado);
    }
}