package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String showHtml(Model model, @RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "size",defaultValue = "10") int size){
        model.addAttribute("pageNumber", 1);
        model.addAttribute("books", bookService.findBooksPaginated(page, size));
        return "book-main";
    }


    @GetMapping("/wishList")
    public String showHtml(Model model, Authentication authentication){
        User user = userRepository.findByLogin(authentication.getName()).get();
        List<Book> wishList = user.getBooks();
        model.addAttribute("books", wishList);
        return "book-wish";
    }


    @GetMapping("/registration")
    public String registration(){
        return "book-registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@RequestParam(name = "login") String login,
                          @RequestParam(name = "password") String password) {
        userRepository.save(User.builder()
                .login(login)
                .password(password)
                .build());
        return "redirect:/";
    }

    @GetMapping("/getAllBooks")
    @ResponseBody
    public long getAllBooks(){
        return bookService.findAllBooks().size();
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Book> findBooks(@RequestParam("searchField") String search){
        if (search.isEmpty()){
            return bookService.findBooksPaginated(1, 10);
        }else return bookService.findBooks(search);
    }

    @PostMapping("/save")
    @ResponseBody
    public List<Book> books(@RequestBody @Valid final Book book) {
        bookService.createBook(book);
        return  bookService.findBooksPaginated(1, 10);
    }

    @GetMapping("/book/{id}")
    public String showHtmlBook(Model model, @PathVariable(name="id") long id){
        Book book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "book";
        }else return "wrong-id";
    }

    @GetMapping("/addWish/{id}")
    public String addDeleteWish(Model model, @PathVariable(name="id") long id, Authentication authentication){
        User user = userRepository.findByLogin(authentication.getName()).get();
        List<Book> wishList = user.getBooks();
        if (wishList.stream().anyMatch(book -> book.getId()==id)) {
            wishList = wishList.stream().dropWhile(book -> book.getId()==id).collect(Collectors.toList());
        } else {
            wishList.add(bookService.findBookById(id));
        }
        user.setBooks(wishList);
        userRepository.save(user);
        return "redirect:/";
    }

}
