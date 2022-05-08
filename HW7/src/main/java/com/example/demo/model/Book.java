package com.example.demo.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column(name = "isbn")
    private String  isbn;

    @Column(name = "book_title")
    private String title;

    @Column(name = "author_name")
    private String author;


}
