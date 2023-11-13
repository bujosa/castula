package com.bujosa.castula.book.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bujosa.castula.book.entity.Book;
import com.bujosa.castula.book.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private Book testBook;

    @BeforeEach
    public void setup() {
        // Create a new book
        testBook = new Book();
        testBook.setName("Test Book");
        testBook.setAuthor("Test Author");

        // Mock the save() and findById() methods of bookRepository
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(testBook);
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(testBook));
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType("application/json")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"));
    }

    @Test
    @Description("Test getting a book by its ID - expect 200 response code")
    public void getById() throws Exception {
        // Perform the GET request using the new book's ID
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + testBook.getId())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"));
    }

    @Test
    @DisplayName("Test creating a new book - expect 200 response code")
    public void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .content("{\"name\": \"Test Book\", \"author\": \"Test Author\"}")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

}
