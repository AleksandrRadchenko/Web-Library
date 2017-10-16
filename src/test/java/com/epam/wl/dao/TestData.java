package com.epam.wl.dao;

import com.epam.wl.entities.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface TestData {
    Book b1 = new Book(1, "Петр Иванов", "Азбука", 1954);
    Book b2 = new Book(2, "Herbert Schildt", "Java: A Beginner's Guide, Sixth Edition", 2014);
    Book b3 = new Book(3, "Bruce Eckel", "Thinking in Java (4th Edition)", 2016);
    Book b4 = new Book(4, "Лев Толстой", "Война и мир", 1978);
    Book b5 = new Book(5, "Jerome David Salinger","The Catcher in the Rye", 2012);
    List<Book> books = new ArrayList<>(Arrays.asList(b1, b2, b3, b4));

}
