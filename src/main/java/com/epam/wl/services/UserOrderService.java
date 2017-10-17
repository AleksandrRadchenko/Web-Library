package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;

import java.sql.SQLException;
import java.util.*;

public class UserOrderService {

    private static UserOrderService instance;
    private final UserOrderDAO userOrderDAO = UserOrderDAO.getInstance();
    private final BookDAO bookDAO = BookDAO.getInstance();

    private UserOrderService() {
    }

    public static synchronized UserOrderService getInstance() {
        if (instance == null) {
            instance = new UserOrderService();
        }
        return instance;
    }

    public List<UserOrder> getNewUserOrders() throws SQLException{
        return userOrderDAO.getUserOrderByStatus(UserOrderStatus.NEW);
    }

    private List<Integer> getFreeBookInstancesForThisBook(int bookId) throws SQLException{
        return bookDAO.getFreeBookInstancesForThisBook(bookId);
    }

    public Map<UserOrder, List<Integer>> getUserOrderAndFreeBookInstanceMap(List<UserOrder> userOrderList) throws SQLException {
        final Map<UserOrder, List<Integer>> resultMap = new TreeMap<>(Comparator.comparingInt(UserOrder::getId));
        for (UserOrder userOrder : userOrderList) {
            resultMap.put(userOrder, getFreeBookInstancesForThisBook(userOrder.getBook().getId()));
        }
        return resultMap;
    }

    public void setUserOrderStatus(int userOrderId, UserOrderStatus newStatus) throws SQLException {
        userOrderDAO.setUserOrderStatus(userOrderId, newStatus);
    }

    public void createNewUserOrder(final int bookID, final int userID) throws SQLException {
        userOrderDAO.createNewUserOrder(bookID, userID);
    }

    public UserOrder getById(int user_orderid) throws SQLException {
        Optional<UserOrder> oUserOrder = userOrderDAO.getUserOrderByID(user_orderid);
        if (!oUserOrder.isPresent()) throw new SQLException("There is no such user_order for id = " + user_orderid);
        else return oUserOrder.get();
    }

    public void deleteNewUserOrder(int userOrderId) throws SQLException {//int bookId, User currentSessionUser
        userOrderDAO.deleteNewUserOrder(userOrderId);
    }
}