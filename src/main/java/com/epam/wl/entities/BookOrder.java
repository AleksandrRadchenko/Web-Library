package com.epam.wl.entities;

import com.epam.wl.enums.BookOption;
import com.epam.wl.enums.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookOrder {
    private int id;
    private int bookInstanceId;
    private int bookId;
    private String author;
    private String title;
    private int year;
    private int userOrderId;
    private int userId;
    private UserOrderStatus status;
    private String name;
    private String lastName;
    private String email;
    private BookOption bookOption;
}
