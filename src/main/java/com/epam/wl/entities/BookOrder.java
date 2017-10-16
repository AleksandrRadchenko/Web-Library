package com.epam.wl.entities;

import com.epam.wl.enums.BookOption;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookOrder {
    private int id;
    private BookInstance bookInstance;
    private UserOrder userOrder;
    private BookOption bookOption;
}
