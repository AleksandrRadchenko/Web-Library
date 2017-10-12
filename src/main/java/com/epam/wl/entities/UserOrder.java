package com.epam.wl.entities;

import com.epam.wl.enums.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserOrder {
    private int id;
    private int userId;
    private String userName;
    private String userLastname;
    private String userEmail;
    private String bookTitle;
    private String bookAuthor;
    private int bookYear;
    private UserOrderStatus status;
}
