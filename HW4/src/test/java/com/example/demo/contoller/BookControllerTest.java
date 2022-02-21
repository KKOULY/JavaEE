package com.example.demo.contoller;

import com.example.demo.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save_book() throws Exception {
        BookDto book = BookDto.builder()
                .author("author1")
                .isbn("isbn1")
                .title("title1")
                .build();
        ArrayList<BookDto> testBooks = new ArrayList<>();
        testBooks.add(book);
        mockMvc.perform(post("/book/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testBooks)));
    }

    @Test
    void find_books() throws Exception {
        BookDto book = BookDto.builder()
                .author("author2")
                .isbn("isbn2")
                .title("title2")
                .build();
        mockMvc.perform(post("/book/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
        ArrayList<BookDto> testBooks = new ArrayList<>();
        testBooks.add(book);
        mockMvc.perform(get("/book/find")
                .param("searchField", "author2"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testBooks)));
    }
}