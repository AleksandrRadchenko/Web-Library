package com.epam.wl.entities;

import com.epam.wl.enums.BookOption;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class BookOrder {
    private int id;
    private int bookInstanceId;
    private int orderId;
    private BookOption bookOption;
}
