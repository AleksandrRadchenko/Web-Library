package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.*;

/**
 * UserOrderService is used to interact with BookDAO and UserOrderDAO and calling servlets to provide the required operations
 */
@Log4j2
public class UserOrderService {

    private static UserOrderService instance;
    private final UserOrderDAO userOrderDAO = UserOrderDAO.getInstance();
    private final BookDAO bookDAO = BookDAO.getInstance();

    private UserOrderService() {
    }

    public static synchronized UserOrderService getInstance() {
        if (instance == null) {
            instance = new UserOrderService();
            log.info("UserOrderService instance created");
        }
        log.info("UserOrderService instance supplied");
        return instance;
    }

    /**
     * Method is used for getting all UserOrders from UserOrderDAO
     * @return
     * @throws SQLException
     */
    public List<UserOrder> getNewUserOrders() throws SQLException{
        return userOrderDAO.getUserOrderByStatus(UserOrderStatus.NEW);
    }

    /**
     * Method is used for getting all Books from BookDAO with required Book.Id which are free
     * @param bookId
     * @return
     * @throws SQLException
     */
    private List<Integer> getFreeBookInstancesForThisBook(int bookId) throws SQLException{
        return bookDAO.getFreeBookInstancesForThisBook(bookId);
    }

    /**
     * Method is used for getting all Books from BookDAO with required Book.author
     * @param userOrderList
     * @return
     * @throws SQLException
     */
    public Map<UserOrder, List<Integer>> getUserOrderAndFreeBookInstanceMap(List<UserOrder> userOrderList) throws SQLException {
        final Map<UserOrder, List<Integer>> resultMap = new TreeMap<>(Comparator.comparingInt(UserOrder::getId));
        for (UserOrder userOrder : userOrderList) {
            resultMap.put(userOrder, getFreeBookInstancesForThisBook(userOrder.getBook().getId()));
        }
        return resultMap;
    }

    /**
     * Method is used for getting all UserOrder from UserOrderDAO with required UserOrder.status
     * @param userOrderId
     * @param newStatus
     * @throws SQLException
     */
    public void setUserOrderStatus(int userOrderId, UserOrderStatus newStatus) throws SQLException {
        userOrderDAO.setUserOrderStatus(userOrderId, newStatus);
    }

    /**
     * Method is used for creating new UserOrder from UserOrderDAO
     * @param bookID
     * @param userID
     * @throws SQLException
     */
    public void createNewUserOrder(final int bookID, final int userID) throws SQLException {
        userOrderDAO.createNewUserOrder(bookID, userID);
    }

    /**
     * Method is used for getting UserOrder from UserOrderDAO with required UserOrder.id
     * @param user_orderid
     * @return
     * @throws SQLException
     */
    public UserOrder getById(int user_orderid) throws SQLException {
        Optional<UserOrder> oUserOrder = userOrderDAO.getUserOrderByID(user_orderid);
        if (!oUserOrder.isPresent()) throw new SQLException("There is no such user_order for id = " + user_orderid);
        else return oUserOrder.get();
    }

    /**
     * Method is used for deleting UserOrder from UserOrderDAO with required UserOrder.id
     * @param userOrderId
     * @throws SQLException
     */
    public void deleteNewUserOrder(int userOrderId) throws SQLException {
        userOrderDAO.deleteNewUserOrder(userOrderId);
    }
}