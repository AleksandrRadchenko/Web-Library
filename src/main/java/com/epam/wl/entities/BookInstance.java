package com.epam.wl.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookInstance {
    private int id;
    private Book book;
}
