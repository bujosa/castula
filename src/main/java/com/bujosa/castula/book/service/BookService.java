package com.bujosa.castula.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bujosa.castula.book.repository.BookRepository;
import com.bujosa.castula.common.exceptions.NotFoundException;
import com.bujosa.castula.book.dtos.BookDto;
import com.bujosa.castula.book.entity.Book;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    Logger log = LoggerFactory.getLogger(BookService.class);

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(String id) throws NotFoundException {
        log.info("Getting book with id: {}", id);
        return bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book not found with id: " + id));
    }

    public Book create(BookDto bookDto) {
        return bookRepository.save(bookDto.toEntity());
    }

    public Book update(String id, BookDto bookDto) throws NotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));
        if (book != null) {
            book.setName(bookDto.getName());
            book.setAuthor(bookDto.getAuthor());
            return bookRepository.save(book);
        }
        return null;
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }

}
