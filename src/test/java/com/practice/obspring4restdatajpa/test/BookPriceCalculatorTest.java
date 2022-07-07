package com.practice.obspring4restdatajpa.test;

import com.practice.obspring4restdatajpa.entities.Book;
import com.practice.obspring4restdatajpa.service.BookPriceCalculator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {

        Book bookTest = new Book(null, "Spring in Action", "Craig Walls", 544, 29.99, LocalDate.of(2015, 1, 1), true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        double price = calculator.calculatePrice(bookTest);
        System.out.println(price);

        assertTrue(price > 0);
        assertEquals(price, 29.99 + 2.99 + 5);
    }
}