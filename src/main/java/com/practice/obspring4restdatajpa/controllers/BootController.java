package com.practice.obspring4restdatajpa.controllers;

import com.practice.obspring4restdatajpa.entities.Book;
import com.practice.obspring4restdatajpa.repositories.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Book> findById(@PathVariable Long id){

        //Functional Programming
        return bookRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

//        return bookRepository.findById(id).orElse(null);
    }


    /**
     * http://localhost:8080/api/books
     * @return
     */

    //Create a new book
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        return bookRepository.save(book);
    }


    /**
     * http://localhost:8080/api/books/{1}
     * @return
     */

    //Update a book
    @PutMapping("/api/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        book.setId(id);
        return bookRepository.save(book);
    }


    /**
     * http://localhost:8080/api/books/{1}
     * @return
     */

    //Delete a book
    @DeleteMapping("/api/books/{id}")
    public void delete(@PathVariable Long id){
        bookRepository.deleteById(id);
    }
}
