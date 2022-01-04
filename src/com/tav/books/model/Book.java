package com.tav.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private float price;

    public Book(String title, float price) {
        this.title = title;
        this.price = price;
    }
}
