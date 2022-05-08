package com.example.demo.service;


import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> findBooks(String search){
        return bookRepository.findBooksByAuthorContainsOrTitleContainsOrIsbnContains(search,search,search);
    }


    @Transactional
    public List<Book> getAllBooks(){
        List<Book> select_u_from_book_u = bookRepository.findAll();
        return select_u_from_book_u;
    }

    @Transactional
    public List<Book> findBooksPaginated(int page, int size){
        Pageable bookPage = PageRequest.of(page - 1, size);
        return bookRepository.findAll(bookPage).getContent();
    }

    @Transactional
    public Book findBookById(long id){
        return bookRepository.findById(id).get();
    }
}
