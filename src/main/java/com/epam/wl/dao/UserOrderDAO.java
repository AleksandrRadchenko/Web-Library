package com.epam.wl.dao;

import com.epam.wl.dao.user_order_handlers.UserOrderListHandler;
import com.epam.wl.dao.user_order_handlers.UserOrderOneHandler;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserOrderDAO {

    private static UserOrderDAO instance;
    private final Executor executor = Executor.getInstance();
    private final ResultHandler<Optional<UserOrder>> userOrderOneHandler = UserOrderOneHandler.getInstance();
    private final ResultHandler<List<UserOrder>> userOrderListHandler = UserOrderListHandler.getInstance();

    public static final String UPDATE_NEW = "INSERT INTO user_order (userid, bookid, status) VALUES (?, ?, 'NEW');";

    public static final String UPDATE_STATUS = "UPDATE user_order SET status = ? WHERE id = ?;";
    // TODO: 16.10.2017
    public static final String QUERY_ALL = "SELECT user_order.id, \"user\".id, \"user\".name, \"user\".lastname, \"user\".email," +
            "book.title, book.author, book.year, user_order.status FROM user_order " +
            "INNER JOIN \"USER\" ON \"USER\".id=user_order.userId INNER JOIN book ON book.id=user_order.bookId;";

    public static final String QUERY_ALL_POST = "SELECT user_order.id AS user_order_id, \"user\".id AS user_id, \"user\".name AS user_name, " +
            "\"user\".lastname AS user_lastname, \"user\".email AS user_email, book.id AS book_id, " +
            "book.title AS title, book.author AS author, book.year AS year, user_order.status AS status FROM user_order " +
            "INNER JOIN \"user\" ON \"user\".id=user_order.userId INNER JOIN book ON book.id=user_order.bookId;";

    public static final String QUERY_BY_ID = "SELECT id, bookid, userid, status FROM user_order WHERE id=?";

    public static final String QUERY_BY_STATUS = "SELECT user_order.id, user.id, user.name, user.lastname, user.email," +
            "book.title, book.author, book.year, user_order.status FROM user_order " +
            "INNER JOIN user ON user.id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE status = ?";

    public static final String QUERY_BY_STATUS_POST = "SELECT user_order.id AS user_order_id, users.id AS user_id, " +
            "users.name AS user_name, users.lastname AS user_lastname, users.email AS user_email,  book.id AS book_id, book.title AS title, " +
            "book.author AS author, book.year AS year, user_order.status AS status FROM user_order INNER JOIN users ON users.id=user_order.userId " +
            "INNER JOIN book ON book.id=user_order.bookId  WHERE status = ?;";

    public static final String QUERY_BY_USER_ID_POST =
            "SELECT user_order.id AS user_order_id, users.id AS user_id, users.name AS user_name, " +
                    "users.lastname AS user_lastname, users.email AS user_email," +
                    "book.id AS book_id, book.title AS title, book.author AS author, book.year AS year, user_order.status AS status " +
                    "FROM user_order INNER JOIN users ON users.id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE user_order.userId = ?;";


//     "SELECT user_order.id AS user_order_id, \"user\".id AS user_id, \"user\".name AS user_name," +
//             "\"user\".lastname AS user_lastname, \"user\".email AS user_email," +
//             "book.title AS book_title, book.author AS book_author, book.year AS book_year, user_order.status AS status" +
//             "FROM user_order INNER JOIN \"user\" ON \"user\".id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE \"user\".id = ?;";

//
//            "SELECT user_order.id, \"user\".id, \"user\".name, \"user\".lastname, \"user\".email," +
//            "book.title, book.author, book.year, user_order.status FROM user_order " +
//            "INNER JOIN \"user\" ON \"user\".id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE \"user\".id = ?";

    public UserOrderDAO() {
    }

    public static synchronized UserOrderDAO getInstance() {
        if (instance == null)
            instance = new UserOrderDAO();
        return instance;
    }

    public void createNewUserOrder(final int bookID, final int userID) throws SQLException {
        executor.executeUpdate(UPDATE_NEW, userID, bookID);
    }

    public void setUserOrderStatus(final int orderID, final UserOrderStatus status) throws SQLException {
        executor.executeUpdate(UPDATE_STATUS, status.toString(), orderID);
    }

    public List<UserOrder> getAllUserOrders() throws SQLException {
        return executor.executeQuery(QUERY_ALL_POST, userOrderListHandler);
    }

    public Optional<UserOrder> getUserOrderByID(final int userOrderID) throws SQLException {
        return executor.executeQuery(QUERY_BY_ID, userOrderOneHandler, userOrderID);
    }

    public List<UserOrder> getUserOrderByStatus(final UserOrderStatus status) throws SQLException {
        return executor.executeQuery(QUERY_BY_STATUS_POST, userOrderListHandler, status.toString());
    }

    public List<UserOrder> getUserOrderByUserId(final int userId) throws SQLException {
        return executor.executeQuery(QUERY_BY_USER_ID_POST, userOrderListHandler, userId);
    }
}
