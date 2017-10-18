package com.epam.wl.services;

import com.epam.wl.dao.BookOrderDAO;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.enums.BookOption;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BookOrderService is used to interact with BookOrderDAO and calling servlets to provide the required operations
 */
public class BookOrderService {
    private static BookOrderService instance;
    private final BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance();

    private BookOrderService(){}

    public static synchronized BookOrderService getInstance(){
        if (instance == null)
            instance = new BookOrderService();
        return instance;
    }

    /**
     * Method is used for creating new BookOrder at BookInstanceDAO
     * @param bookInstanceId
     * @param userOrderId
     * @param bookOption
     * @throws SQLException
     */
    public void create(int bookInstanceId, int userOrderId, BookOption bookOption) throws SQLException {
        bookOrderDAO.create(bookInstanceId, userOrderId, bookOption);
    }

    /**
     * Method is used for getting all BookOrders from BookOrderDAO
     * @return list of BookOrder
     * @throws SQLException
     */
    public List<BookOrder> getAll() throws SQLException {
        return bookOrderDAO.getAll();
    }

    /**
     * Method is used for getting all BookOrder from BookOrderDAO bu userID
     * @param userId
     * @return list of BookOrder
     * @throws SQLException
     */
    public List<BookOrder> getByUserId(int userId) throws SQLException {
        return bookOrderDAO.getByUserId(userId);
    }

    /**
     * Method is used for getting all BookOrders from BookOrderDAO with users, having orders
     * @return list of User
     * @throws SQLException
     */
    public List<User> getAllUsersWithOrders() throws SQLException {
        List<BookOrder> bookOrders = getAll();
        List<User> result = new ArrayList<>();
        for (BookOrder bookorder : bookOrders) {
            result.add(bookorder.getUserOrder().getUser());
        }
        return result;
    }

    /**
     * Method is used for deleting BookOrder from BookOrderDAO using UserOrderID
     * @param userOrderId
     * @throws SQLException
     */
    public void deleteById(int userOrderId) throws SQLException {
        bookOrderDAO.deleteById(userOrderId);
    }
}
