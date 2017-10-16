package com.epam.wl.services;

import com.epam.wl.DBHelper;
import com.epam.wl.dao.BookOrderDAO;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.enums.BookOption;
import com.epam.wl.enums.UserRole;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookOrderService {
    private static BookOrderService instance;
    private DataSource dataSource = DBHelper.getEmbeddedDatabase();
    private BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance(dataSource);

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

    public List<User> getAllUsers() {
        List<BookOrder> bookOrders = getAll();
        Set<User> result = new HashSet<>();
        for (BookOrder bookorder : bookOrders) {
            result.add(new User(bookorder.getUserId(), bookorder.getName(), bookorder.getLastName(), bookorder.getEmail(), "", UserRole.USER));
        }
        return new ArrayList<>(result);
    }
}
