package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("")
    public String bookMain(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "book-main";
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Book> findBook(@RequestParam("searchField") String search){
        if (search.isEmpty()){
            return bookService.getAllBooks();
        }else return bookService.findBooks(search);
    }

    @PostMapping("/save")
    @ResponseBody
    public List<Book> saveBook(@RequestBody final Book book) {
        bookService.createBook(book);
        return  bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public String bookPage(Model model, @PathVariable(name="id") long id){
        Book book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "book";
        }else return "wrong_id";
    }

}
