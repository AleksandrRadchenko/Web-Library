package com.epam.wl.entities;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String author;
    private String title;
    private int year;

    public Book(int id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public static void main(String[] args) {
        Book book = new Book(4, "Лев Толстой", "Война и мир", 1978);
        System.out.println(book.getAuthor());
    }
}
