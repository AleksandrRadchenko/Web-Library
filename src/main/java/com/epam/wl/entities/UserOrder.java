package com.epam.wl.entities;

import com.epam.wl.enums.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserOrder {
    private int id;
    private User user;
    private Book book;
    private UserOrderStatus status;
}
