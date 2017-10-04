package com.epam.wl.dao;

import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.Order;
import com.epam.wl.enums.BookOptions;

import java.util.List;

public interface BookOrderDAO {
    void create(Order order, BookInstance bookInstance, BookOptions bookOption);

    List<Order> getAll();

    List<Order> getNew();

}
