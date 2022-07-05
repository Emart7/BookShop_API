package com.practice.obspring4restdatajpa;

import com.practice.obspring4restdatajpa.entities.Book;
import com.practice.obspring4restdatajpa.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObSpring4restDatajpaApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ObSpring4restDatajpaApplication.class, args);
        BookRepository repository = context.getBean(BookRepository.class);

        //CRUD operations
        //Create a new book
        Book book1 = new Book(null, "Spring in Action", "Craig Walls", 544, 29.99, LocalDate.of(2015, 1, 1), true);
        Book book2 = new Book(null, "La ultima Jugada", "Robin Mess", 340, 19.99, LocalDate.of(2016, 1, 1), true);

        System.out.println("Numero de libros en la base de datos." + repository.findAll().size());
        //Save the book
        repository.save(book1);
        repository.save(book2);

        //Find all books
        for (Book book : repository.findAll()) {
            System.out.println(book.toString());
        }
        System.out.println("Numero de libros en la base de datos." + repository.findAll().size());

        //Delete the book
//        repository.delete(book1);
//        System.out.println("Numero de libros en la base de datos." + repository.findAll().size());




    }

}
