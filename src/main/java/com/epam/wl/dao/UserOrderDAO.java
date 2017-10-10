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

    public UserOrderDAO(DataSource dataSource) {
        this.executor = new Executor(dataSource);
        this.userOrderListHandler = new UserOrderListHandler();
        this.userOrderOneHandler = new UserOrderOneHandler();
    }

    public void createNewUserOrder(final int bookID, final int userID) throws SQLException {
        String update = "INSERT INTO user_order (userid, bookid, status) VALUES(?, ?,'NEW');";
        executor.executeUpdate(update, String.valueOf(userID), String.valueOf(bookID));
    }

    public void setUserOrderStatus(final int orderID, final UserOrderStatus status) throws SQLException {
        String update = "UPDATE user_order SET status = ? WHERE id = ?";
        executor.executeUpdate(update, String.valueOf(status), String.valueOf(orderID));
    }

    public List<UserOrder> getAllUserOrders() throws SQLException {
        String query = "SELECT * FROM user_order";
        return executor.executeQuery(query, userOrderListHandler);
    }

    public Optional<UserOrder> getUserOrderByID(final int userOrderID) throws SQLException {
        String query = "SELECT * FROM user_order WHERE id = ?";
        return executor.executeQuery(query, userOrderOneHandler, String.valueOf(userOrderID));
    }

    public List<UserOrder> getUserOrderByStatus(final UserOrderStatus status) throws SQLException {
        String query = "SELECT * FROM user_order WHERE status = ?";
        return executor.executeQuery(query, userOrderListHandler, String.valueOf(status));
    }
}
