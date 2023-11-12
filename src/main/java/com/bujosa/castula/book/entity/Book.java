package com.bujosa.castula.book.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
