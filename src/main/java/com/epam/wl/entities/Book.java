package com.epam.wl.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Book {
    private int id;
    private String author;
    private String title;
    private int year;

    public static void main(String[] args) {
        Book book = new Book(4, "Лев Толстой", "Война и мир", 1978);
        System.out.println(book.getAuthor());
    }
}
