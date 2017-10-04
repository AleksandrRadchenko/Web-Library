package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.Order;
import com.epam.wl.entities.User;

import java.util.List;

public interface OrderDAO {
    void create(User user, Book book);

    void setInProgress(int orderID);

    void close(int orderID);

    List<Order> getAll();

    List<Order> getNew();

}
