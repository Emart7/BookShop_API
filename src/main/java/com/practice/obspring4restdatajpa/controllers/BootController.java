package com.practice.obspring4restdatajpa.controllers;

import com.practice.obspring4restdatajpa.entities.Book;
import com.practice.obspring4restdatajpa.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BootController {

    private BookRepository bookRepository;

    public BootController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //CRUD sobre la entidad Book


    /**
     * http://localhost:8080/api/books
     * @return
     */

    //Find all books
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }


    /**
     * http://localhost:8080/api/books/{1}
     * @return
     */

    //Buscar un libro segun su id
    @GetMapping("/api/books/{id}")
    public Book findById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * http://localhost:8080/api/books/{1}
     * @return
     */

    //Create a new book
    @PostMapping("/api/post/books")
    public Book create(Book book){
        return bookRepository.save(book);
    }

    //Update a book

    //Delete a book
}
