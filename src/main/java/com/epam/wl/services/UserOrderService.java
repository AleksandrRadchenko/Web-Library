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

    public List<UserOrder> getNewUserOrders() {
        List<UserOrder> resultList = new ArrayList<>();
        try {
            resultList = userOrderDAO.getUserOrderByStatus(UserOrderStatus.NEW);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private List<Integer> getFreeBookInstancesForThisBook(int bookId) {
        List<Integer> resultList = new ArrayList<>();
        try {
            resultList = bookDAO.getFreeBookInstancesForThisBook(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public Map<UserOrder, List<Integer>> getUserOrderAndFreeBookInstanceMap(List<UserOrder> userOrderList) {
        final Map<UserOrder, List<Integer>> resultMap = new TreeMap<>(Comparator.comparingInt(UserOrder::getId));
        userOrderList.forEach(userOrder -> resultMap.put(userOrder, getFreeBookInstancesForThisBook(userOrder.getBook().getId())));
        return resultMap;
    }

    public void setUserOrderStatus(int userOrderId, UserOrderStatus newStatus) {
        try {
            userOrderDAO.setUserOrderStatus(userOrderId, newStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewUserOrder(final int bookID, final int userID) {
        try {
            userOrderDAO.createNewUserOrder(bookID, userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}