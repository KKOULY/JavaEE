package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    @Sql(value = "/BookService/clean_up.sql")
    void createBook() {
        bookService.createBook(new Book());
        assertThat(bookService.getAllBooks()).hasSize(1);
    }

    @Test
    @Sql(value = "/BookService/clean_up.sql")
    @Sql("/BookService/init.sql")
    void findBooks() {
        assertThat(bookService.findBooks("testTitle")).hasSize(4);
    }


    @Test
    @Sql(value = "/BookService/clean_up.sql")
    @Sql("/BookService/init.sql")
    void findAllBooks() {
        assertThat(bookService.getAllBooks()).hasSize(5);
    }

    @Test
    @Sql(value = "/BookService/clean_up.sql")
    @Sql("/BookService/init.sql")
    void findBookById() {
        assertThat(bookService.findBookById(1)).returns(1L, Book::getId).returns("testTitle1", Book::getTitle);
    }
}