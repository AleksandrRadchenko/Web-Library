package com.epam.wl.services;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;

import java.util.ArrayList;
import java.util.List;

//Instead of DB connection. Just for example

public class TestUserOrderService {
    public static List<UserOrder> getOrders(){
        List<UserOrder> list = new ArrayList<>();
        list.add(new UserOrder(1, 1, 1, UserOrderStatus.IN_PROGRESS));
        list.add(new UserOrder(2, 2, 2, UserOrderStatus.IN_PROGRESS));
        list.add(new UserOrder(3, 3, 3, UserOrderStatus.IN_PROGRESS));
        list.add(new UserOrder(4, 4, 4, UserOrderStatus.IN_PROGRESS));
        return list;
    }
}
