package com.example.book_store_security.Services;

import com.example.book_store_security.Repositories.AuthorRepository;
import com.example.book_store_security.domains.Author;
import com.example.book_store_security.dtos.AuthorRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServices {

    private final AuthorRepository authorRepository;

    @Transactional
    public Author saveAuthor(AuthorRequestDTO author){

        if(authorRepository.findByName(author.name()) != null) throw new RuntimeException("Nome ja registrado no sistema.");

        Author authorSaved = new Author(author);
        return authorRepository.save(authorSaved);
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    public  Author getAuthorByName(String name){
        return authorRepository.findByName(name);
    }

    public String deleteAuthorByName(String name){

        var authorDeleted = authorRepository.findByName(name);
        if(authorDeleted==null)
            throw new RuntimeException("Nome não foi encontrado no registro.");
        authorRepository.delete(authorDeleted);
        return "Deletado com sucesso!";
    }

    @Transactional
    public Author updateAuthorRequested(AuthorRequestDTO authorRequestDTO) {
        var authorUpdated = authorRepository.findByName(authorRequestDTO.name());
        if (authorUpdated == null)throw new RuntimeException("Falha na atualização! Autor não foi encontrado.");

        authorUpdated.setAge(authorRequestDTO.age());
        authorUpdated.setName(authorRequestDTO.name());
        authorUpdated.setCountry(authorRequestDTO.country());
        return authorRepository.save(authorUpdated);
    }
}
