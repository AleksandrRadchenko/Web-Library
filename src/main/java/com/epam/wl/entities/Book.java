package com.epam.wl.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Book {
    private int id;
    private String author;
    private String title;
    private int year;
}
