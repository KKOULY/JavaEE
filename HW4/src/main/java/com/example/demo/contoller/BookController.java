package com.example.demo.contoller;

import com.example.demo.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/book")
public class BookController {

    private static final ArrayList<BookDto> BOOK_DATABASE = new ArrayList();


    @GetMapping("")
    public String bookMain(Model model){
        model.addAttribute("books", BOOK_DATABASE);
        return "book-main";
    }

    @PostMapping("/save")
    @ResponseBody
    public ArrayList<BookDto> books(@RequestBody final BookDto book) {
        BOOK_DATABASE.add(book);
        return BOOK_DATABASE;
    }

    @GetMapping("/find")
    @ResponseBody
    public ArrayList<BookDto> find_books(@RequestParam("searchField") String search){
        if (search.isEmpty()){
            return BOOK_DATABASE;
        }
        ArrayList<BookDto> bookFindList = new ArrayList<>();
        for (BookDto b:
                BOOK_DATABASE) {
            if (b.getAuthor().contains(search) || b.getIsbn().contains(search) || b.getTitle().contains(search)){
                bookFindList.add(b);
            }
        }
        return bookFindList;
    }

    @GetMapping("/clear")
    @ResponseBody
    public void clear(){
        BOOK_DATABASE.clear();
    }
}
