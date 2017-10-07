package com.epam.wl.entities;

import com.epam.wl.enums.OrderStatus;
import lombok.Data;

@Data
public class UserOrder {
    private int id;
    private int editionId;
    private int userId;
    private OrderStatus status;
}
