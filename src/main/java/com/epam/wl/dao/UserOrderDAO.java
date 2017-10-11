package com.epam.wl.dao;

import com.epam.wl.dao.user_order_handlers.UserOrderListHandler;
import com.epam.wl.dao.user_order_handlers.UserOrderOneHandler;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserOrderDAO {

    private Executor executor;
    private final ResultHandler<Optional<UserOrder>> userOrderOneHandler;
    private final ResultHandler<List<UserOrder>> userOrderListHandler;

    public static final String UPDATE_NEW = "INSERT INTO user_order (userid, bookid, status) VALUES(?, ?,'NEW');";
    public static final String UPDATE_STATUS = "UPDATE user_order SET status = ? WHERE id = ?";
    public static final String QUERY_ALL = "SELECT * FROM user_order";
    public static final String QUERY_BY_ID = "SELECT * FROM user_order WHERE id = ?";
    public static final String QUERY_BY_STATUS = "SELECT * FROM user_order WHERE status = ?";

    public UserOrderDAO(DataSource dataSource) {
        this.executor = new Executor(dataSource);
        this.userOrderListHandler = new UserOrderListHandler();
        this.userOrderOneHandler = new UserOrderOneHandler();
    }

    public void createNewUserOrder(final int bookID, final int userID) throws SQLException {
        executor.executeUpdate(UPDATE_NEW, String.valueOf(userID), String.valueOf(bookID));
    }

    public void setUserOrderStatus(final int orderID, final UserOrderStatus status) throws SQLException {
        executor.executeUpdate(UPDATE_STATUS, String.valueOf(status), String.valueOf(orderID));
    }

    public List<UserOrder> getAllUserOrders() throws SQLException {
        return executor.executeQuery(QUERY_ALL, userOrderListHandler);
    }

    public Optional<UserOrder> getUserOrderByID(final int userOrderID) throws SQLException {
        return executor.executeQuery(QUERY_BY_ID, userOrderOneHandler, String.valueOf(userOrderID));
    }

    public List<UserOrder> getUserOrderByStatus(final UserOrderStatus status) throws SQLException {
        return executor.executeQuery(QUERY_BY_STATUS, userOrderListHandler, String.valueOf(status));
    }
}
