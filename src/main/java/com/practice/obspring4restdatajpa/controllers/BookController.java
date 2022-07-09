package com.practice.obspring4restdatajpa.controllers;

import com.practice.obspring4restdatajpa.entities.Book;
import com.practice.obspring4restdatajpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
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

    //Search a workbook by id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){

        //Functional Programming
        return bookRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//        return bookRepository.findById(id).orElse(null);

        //Option 2
//        Optional<Book> bookOpt = bookRepository.findById(id);
//        if(bookOpt.isPresent()){
//            return ResponseEntity.ok(bookOpt.get());
//        }else{
//            return ResponseEntity.notFound().build();
//        }
    }


    /**
     * http://localhost:8080/api/books
     * @return
     */

    //Create a new book
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));//This line is optional

        if(book.getId() != null){
            log.warn("Trying to create a book with an id");
            return ResponseEntity.badRequest().build();
        }
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }


    /**
     * http://localhost:8080/api/books
     * http://localhost:8080/api/books/{1}
     * @return
     */

    //Update a book
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
//        @PathVariable Long id = /api/books/{id}
        if (book.getId() == null){
            log.warn("Trying to update a book without an id");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepository.existsById((book.getId()))){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
//        book.setId(id);
        Book updateBook = bookRepository.save(book);
        return ResponseEntity.ok(updateBook);
    }


    /**
     * http://localhost:8080/api/books/{1}
     * @return
     */

    //Delete a book
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if (!bookRepository.existsById((id))){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
