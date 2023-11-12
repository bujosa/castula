package com.bujosa.castula.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bujosa.castula.book.dtos.BookDto;
import com.bujosa.castula.book.entity.Book;
import com.bujosa.castula.book.service.BookService;
import com.bujosa.castula.common.exceptions.NotFoundException;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") String id) throws NotFoundException {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.create(bookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") String id, @RequestBody BookDto bookDto)
            throws NotFoundException {
        return ResponseEntity.ok(bookService.update(id, bookDto));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
