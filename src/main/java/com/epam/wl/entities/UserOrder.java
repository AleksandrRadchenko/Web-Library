package com.epam.wl.entities;

import com.epam.wl.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserOrder {
    private int id;
    private int bookId;
    private int userId;
    private OrderStatus status;
}
