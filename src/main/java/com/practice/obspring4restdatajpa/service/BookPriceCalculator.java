package com.practice.obspring4restdatajpa.service;

import com.practice.obspring4restdatajpa.entities.Book;

public class BookPriceCalculator {

    public double calculatePrice(Book book){

        double price = book.getPrice();
        double shipping = 2.99;

        if (book.getPages() > 300){
            price += 5;
        }
        price += shipping;

        return price;
    }
}
