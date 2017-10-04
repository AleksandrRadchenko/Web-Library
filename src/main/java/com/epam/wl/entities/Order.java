package com.epam.wl.entities;

import com.epam.wl.enums.Status;
import lombok.Data;

@Data
public class Order {
    private int id;
    private int editionId;
    private int userId;
    private Status status;

    public Order(int id, int editionId, int userId, Status status) {
        this.id = id;
        this.editionId = editionId;
        this.userId = userId;
        this.status = status;
    }
}
