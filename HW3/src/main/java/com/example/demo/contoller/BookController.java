package com.example.demo.contoller;

import com.example.demo.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class BookController {

    private static final ArrayList<BookDto> BOOK_DATABASE = new ArrayList();

    @GetMapping("/book-create")
    public String bookForm(){
        return "book-create";
    }

    @PostMapping("/book-create")
    public String saveBook(final BookDto bookDto){
        System.out.println("Save book:"+bookDto);
        BOOK_DATABASE.add(bookDto);
        return "redirect:/saved-books";
    }

    @GetMapping("/saved-books")
    public String bookList(final Model model){
        model.addAttribute("books", BOOK_DATABASE);
        return "saved-books";
    }
}
