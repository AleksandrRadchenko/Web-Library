package com.epam.wl.entities;

import com.epam.wl.enums.BookOptions;
import lombok.Value;

@Value
public class BookOrder {
    private int id;
    private int bookInstanceId;
    private int orderId;
    private BookOptions bookOption;
}
