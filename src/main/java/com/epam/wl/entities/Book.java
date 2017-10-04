package com.epam.wl.entities;

import com.epam.wl.enums.Options;
import lombok.Data;

@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
}
