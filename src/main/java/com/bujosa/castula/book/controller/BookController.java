package com.bujosa.castula.book.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bujosa.castula.book.entity.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        Book book = new Book(1L, "El Quijote", "Miguel de Cervantes");
        List<Book> books = List.of(book);

        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> createBook() {
        Book book = new Book(1L, "El Quijote", "Miguel de Cervantes");

        return ResponseEntity.ok(book);
    }

}
