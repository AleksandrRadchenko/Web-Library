package com.epam.wl.dao;

import com.epam.wl.dao.user_order_handlers.UserOrderListHandler;
import com.epam.wl.dao.user_order_handlers.UserOrderOneHandler;
import com.epam.wl.entities.User;
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

    public static final String QUERY_ALL_POST = "SELECT user_order.id AS user_order_id, users.id AS user_id, " +
            "book.id AS book_id, user_order.status AS status " +
            "FROM user_order INNER JOIN users ON users.id=user_order.userId INNER JOIN book ON book.id=user_order.bookId;";

    public static final String QUERY_BY_ID = "SELECT id AS user_order_id, bookid AS book_id, userid AS user_id, status FROM user_order WHERE id=?";

    public static final String QUERY_BY_STATUS_POST = "SELECT user_order.id AS user_order_id, users.id AS user_id, " +
            "book.id AS book_id, user_order.status AS status " +
            "FROM user_order INNER JOIN users ON users.id=user_order.userId " +
            "INNER JOIN book ON book.id=user_order.bookId  WHERE status = ?;";

    public static final String QUERY_BY_USER_ID_POST =
            "SELECT user_order.id AS user_order_id, users.id AS user_id, " +
                    "book.id AS book_id, user_order.status AS status " +
                    "FROM user_order INNER JOIN users ON users.id=user_order.userId INNER JOIN book ON book.id=user_order.bookId WHERE user_order.userId = ?;";

    public static final String UPDATE_DELETE_BY_BOOK_ID_AND_USER_ID =
            "DELETE FROM user_order WHERE status='NEW' AND user_order.id=?;";
    //"DELETE FROM user_order WHERE status='NEW' AND bookid=? AND userid=?;";

    private UserOrderDAO() {
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

    public void deleteNewUserOrder(int userOrderId) throws SQLException {//int bookId, User currentSessionUser
        executor.executeUpdate(UPDATE_DELETE_BY_BOOK_ID_AND_USER_ID, userOrderId);//bookId, currentSessionUser.getId()
    }
}
