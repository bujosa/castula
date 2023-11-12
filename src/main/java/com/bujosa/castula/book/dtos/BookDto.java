package com.bujosa.castula.book.dtos;

import java.time.Instant;

import com.bujosa.castula.book.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private String author;

    public BookDto(String name, String author) {
        // Auto generate the id for the book
        this.id = Instant.now().toString();
        this.name = name;
        this.author = author;
    }

    public Book toEntity() {
        return new Book(this.name, this.author);
    }
}
