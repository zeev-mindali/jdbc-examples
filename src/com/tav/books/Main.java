package com.tav.books;


import com.tav.books.dal.BookDAL;
import com.tav.books.model.Book;

public class Main {
    public static void main(String[] args) {
        BookDAL bookDAL = BookDAL.instance;
        bookDAL.create(new Book("Effective java", 70));
        System.out.println(bookDAL.readAll());
    }
}
