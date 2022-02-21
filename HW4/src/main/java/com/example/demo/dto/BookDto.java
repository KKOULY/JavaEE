package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

    private String title;
    private String isbn;
    private String author;
}
