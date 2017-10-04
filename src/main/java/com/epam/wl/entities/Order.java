package com.epam.wl.entities;

import com.epam.wl.enums.OrderStatus;
import lombok.Data;

@Data
public class Order {
    private int id;
    private int editionId;
    private int userId;
    private OrderStatus status;

    public Order(int id, int editionId, int userId, OrderStatus status) {
        this.id = id;
        this.editionId = editionId;
        this.userId = userId;
        this.status = status;
    }
}
