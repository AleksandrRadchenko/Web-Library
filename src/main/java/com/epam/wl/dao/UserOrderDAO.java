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

    public static final String UPDATE_NEW = "INSERT INTO user_order (userid, bookid, status) VALUES (?, ?, 'NEW');";

    public static final String UPDATE_STATUS = "UPDATE user_order SET status = ? WHERE id = ?;";

    public static final String QUERY_ALL = "SELECT user_order.id, \"user\".id, \"user\".name, \"user\".lastname, \"user\".email," +
            "book.title, book.author, book.year, user_order.status FROM user_order " +
            "INNER JOIN \"USER\" ON \"USER\".id=user_order.userId INNER JOIN book ON book.id=user_order.bookId;";

    public static final String QUERY_ALL_POST = "SELECT user_order.id AS user_order_id, \"user\".id AS user_id, \"user\".name AS user_name, " +
            "\"user\".lastname AS user_lastname, \"user\".email AS user_email, book.id AS book_id, " +
            "book.title AS title, book.author AS author, book.year AS year, user_order.status AS status FROM user_order " +
            "INNER JOIN \"user\" ON \"user\".id=user_order.userId INNER JOIN book ON book.id=user_order.bookId;";

    public static final String QUERY_BY_ID = "SELECT user_order.id, user.id, user.name, user.lastname, user.email, " +
            "book.title, book.author, book.year, user_order.status FROM user_order " +
            "INNER JOIN user ON user.id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE user_order.id=?";

    public static final String QUERY_BY_STATUS = "SELECT user_order.id, user.id, user.name, user.lastname, user.email," +
            "book.title, book.author, book.year, user_order.status FROM user_order " +
            "INNER JOIN user ON user.id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE status = ?";

    public static final String QUERY_BY_STATUS_POST = "SELECT user_order.id AS user_order_id, \"user\".id AS user_id, " +
            "\"user\".name AS user_name, \"user\".lastname AS user_lastname, \"user\".email AS user_email,  book.id AS book_id, book.title AS title, " +
            "book.author AS author, book.year AS year, user_order.status AS status FROM user_order INNER JOIN \"user\" ON \"user\".id=user_order.userId " +
            "INNER JOIN book ON book.id=user_order.bookId  WHERE status = ?;";

    public static final String QUERY_BY_USER_ID = "SELECT user_order.id, user.id, user.name, user.lastname, user.email," +
            "book.title, book.author, book.year, user_order.status FROM user_order " +
            "INNER JOIN user ON user.id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE user.id = ?";

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
        return executor.executeQuery(QUERY_ALL_POST, userOrderListHandler);
    }

    public Optional<UserOrder> getUserOrderByID(final int userOrderID) throws SQLException {
        return executor.executeQuery(QUERY_BY_ID, userOrderOneHandler, String.valueOf(userOrderID));
    }

    public List<UserOrder> getUserOrderByStatus(final UserOrderStatus status) throws SQLException {
        return executor.executeQuery(QUERY_BY_STATUS_POST, userOrderListHandler, String.valueOf(status));
    }

    public List<UserOrder> getUserOrderByUserId(final int userId) throws SQLException {
        return executor.executeQuery(QUERY_BY_USER_ID, userOrderListHandler, String.valueOf(userId));
    }
}
