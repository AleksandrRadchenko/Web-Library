package com.epam.wl.services;

import com.epam.wl.dao.BookOrderDAO;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.enums.BookOption;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookOrderService {
    private static BookOrderService instance;
    private final BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance();

    private BookOrderService(){}

    public static synchronized BookOrderService getInstance(){
        if (instance == null)
            instance = new BookOrderService();
        return instance;
    }

    public void create(int bookInstanceId, int userOrderId, BookOption bookOption) {
        try {
            bookOrderDAO.create(bookInstanceId, userOrderId, bookOption);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BookOrder> getAll() {
        List<BookOrder> result = new ArrayList<>();
        try {
            result = bookOrderDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<BookOrder> getByUserId(int userId) {
        List<BookOrder> result = new ArrayList<>();
        try {
            result = bookOrderDAO.getByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getAllUsersWithOrders() {
        List<BookOrder> bookOrders = getAll();
        List<User> result = new ArrayList<>();
        for (BookOrder bookorder : bookOrders) {
            result.add(bookorder.getUserOrder().getUser());
        }
        return result;
    }
}
