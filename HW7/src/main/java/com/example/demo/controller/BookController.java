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
    public String showHtml(Model model, @RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "size",defaultValue = "10") int size){
        model.addAttribute("pageNumber", 1);
        model.addAttribute("books", bookService.findBooksPaginated(page, size));
        return "book-main";
    }


    @GetMapping("/getAllBooks")
    @ResponseBody
    public long find_all_books(){
        return bookService.getAllBooks().size();
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Book> find_books(@RequestParam("searchField") String book){
        if (book.isEmpty()){
            return bookService.findBooksPaginated(1, 10);
        } else return bookService.findBooks(book);
    }

    @PostMapping("/save")
    @ResponseBody
    public List<Book> books(@RequestBody final Book book) {
        bookService.createBook(book);
        return  bookService.findBooksPaginated(1, 10);
    }

    @GetMapping("/book/{id}")
    public String showHtmlBook(Model model, @PathVariable(name="id") long id){
        Book book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "book";
        } else return "wrong_id";
    }

}
