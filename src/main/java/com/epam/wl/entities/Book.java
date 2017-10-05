package com.epam.wl.entities;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private int year;

    public static void main(String[] args) {
        Book book = new Book();
        System.out.println(book.getAuthor());
    }
}
