package com.example.book_store_security.domains;

import com.example.book_store_security.dtos.AuthorRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String country;

    @ManyToMany(mappedBy = "author", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Book> books = new HashSet<>();

    public Author(AuthorRequestDTO data)
    {
        name = data.name();
        age = data.age();
        country = data.country();
    }

}
