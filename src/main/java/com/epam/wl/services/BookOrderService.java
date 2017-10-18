package com.epam.wl.services;

import com.epam.wl.dao.BookOrderDAO;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.enums.BookOption;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookOrderService {
    private static BookOrderService instance;
    private final BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance();

    private BookOrderService(){}

    public static synchronized BookOrderService getInstance(){
        if (instance == null)
            instance = new BookOrderService();
        return instance;
    }

    public void create(int bookInstanceId, int userOrderId, BookOption bookOption) throws SQLException {
        bookOrderDAO.create(bookInstanceId, userOrderId, bookOption);
        log.info("Created book order ({}, {}, {})", bookInstanceId, userOrderId, bookOption);
    }

    public List<BookOrder> getAll() throws SQLException {
        return bookOrderDAO.getAll();
    }

    public List<BookOrder> getByUserId(int userId) throws SQLException {
        return bookOrderDAO.getByUserId(userId);
    }

    public List<User> getAllUsersWithOrders() throws SQLException {
        List<BookOrder> bookOrders = getAll();
        List<User> result = new ArrayList<>();
        for (BookOrder bookorder : bookOrders) {
            result.add(bookorder.getUserOrder().getUser());
        }
        return result;
    }

    public void deleteById(int userOrderId) throws SQLException {
        bookOrderDAO.deleteById(userOrderId);
        log.info("Deleted book order id={}");
    }
}
