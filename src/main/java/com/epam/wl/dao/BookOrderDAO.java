package com.epam.wl.dao;

import com.epam.wl.dao.book_order_handlers.BookOrderListHandler;
import com.epam.wl.dao.book_order_handlers.BookOrderOneHandler;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOption;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookOrderDAO {
    private static BookOrderDAO instance;
    private final Executor executor = Executor.getInstance();
    private final ResultHandler<Optional<BookOrder>> bookOrderOneHandler = BookOrderOneHandler.getInstance();
    private final ResultHandler<List<BookOrder>> bookOrderListHandler = BookOrderListHandler.getInstance();

    private final static String ALL_FIELDS = "book_order.id, book_instanceid, bookid, author, title, " +
            "year, user_orderid, userid, status, name, lastname, email, option";
    private final static String JOIN_4_TABLES = "book_order INNER JOIN user_order ON user_order.id=book_order.user_orderid " +
            "INNER JOIN book ON book.id=user_order.bookId INNER JOIN user ON user.id=user_order.userid";
    // language=H2
    private final static String QUERY_CREATE = "INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES(?, ?, ?);";
    // language=H2
    private final static String QUERY_GET_ALL = "SELECT id AS book_order_id, book_instanceid, user_orderid, option FROM book_order";
    // language=H2
    private final static String QUERY_GET_BY_USER_ID =
            "SELECT book_order.id AS book_order_id, book_instanceid, user_orderid, option " +
                    "FROM book_order INNER JOIN user_order ON user_order.id=book_order.user_orderid WHERE userid=?";
    // language=H2
    private final static String QUERY_GET_BY_ID = "SELECT id AS book_order_id, book_instanceid, user_orderid, option FROM book_order WHERE id=?";
    // language=H2
    private final static String QUERY_UPDATE = "UPDATE book_order SET book_instanceid=?, user_orderid=?, option=? WHERE id=?";

    private BookOrderDAO() {
    }

    public static synchronized BookOrderDAO getInstance() {
        if (instance == null)
            instance = new BookOrderDAO();
        return instance;
    }

    /**
     * Create row in book_order table
     *
     * @param bookInstanceId int
     * @param userOrderId    int
     * @param bookOption     enum
     * @return 1 (number of rows changed) if success, or throws SQLexception
     * @throws SQLException
     */
    @SuppressWarnings("JavaDoc")
    public void create(
            final int bookInstanceId,
            final int userOrderId,
            final BookOption bookOption)
            throws SQLException {
        executor.executeUpdate(QUERY_CREATE, bookInstanceId, userOrderId, String.valueOf(bookOption.toString()));
    }

    public List<BookOrder> getAll() throws SQLException {
        return executor.executeQuery(QUERY_GET_ALL, bookOrderListHandler);
    }

    public List<BookOrder> getByUserId(int id) throws SQLException {
        return executor.executeQuery(QUERY_GET_BY_USER_ID, bookOrderListHandler, id);
    }

    public Optional<BookOrder> getById(final int id) throws SQLException {
        return executor.executeQuery(QUERY_GET_BY_ID, bookOrderOneHandler, id);
    }
}
