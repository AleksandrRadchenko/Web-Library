package com.epam.wl.dao;

import com.epam.wl.dao.book_order_handlers.BookOrderListHandler;
import com.epam.wl.dao.book_order_handlers.BookOrderOneHandler;
import com.epam.wl.dao.book_order_handlers.PrintlnHandler;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOption;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookOrderDAO {
    private Executor executor;
    private final ResultHandler<Optional<BookOrder>> bookOrderOneHandler = new BookOrderOneHandler();
    private final ResultHandler<List<BookOrder>> bookOrderListHandler = new BookOrderListHandler();

    // language=H2
    private final static String QUERY_CREATE = "INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES(?, ?, ?);";
    private final static String QUERY_GET_ALL = "SELECT id, book_instanceid, user_orderid, option FROM book_order";
    private final static String QUERY_GET_BY_USER_ID =
            "SELECT book_order.id, book.author, book.title, book.year, user_order.status, user.name, user.lastname " +
            "FROM book_order INNER JOIN user_order ON user_order.id=book_order.user_orderid " +
            "INNER JOIN book ON book.id=user_order.bookId INNER JOIN user ON user.id=user_order.userid WHERE user.id=?";
    private final static String QUERY_GET_BY_ID = "SELECT id, book_instanceid, user_orderid, option FROM book_order WHERE id=?";
    private final static String QUERY_UPDATE = "UPDATE book_order SET book_instanceid=?, user_orderid=?, option=?";
    private final static String QUERY_DELETE = "DELETE FROM book_order WHERE id = ?";

    public BookOrderDAO(DataSource dataSource) {
        executor = new Executor(dataSource);
    }

    /**
     * Create row in book_order table
     * @param bookInstanceId int
     * @param userOrderId int
     * @param bookOption enum
     * @return 1 (number of rows changed) if success, or throws SQLexception
     * @throws SQLException
     */
    @SuppressWarnings("JavaDoc")
    public void create(
            final int bookInstanceId,
            final int userOrderId,
            final BookOption bookOption)
            throws SQLException {
        executor.executeUpdate(QUERY_CREATE, String.valueOf(bookInstanceId), String.valueOf(userOrderId), String.valueOf(bookOption.toString()));
    }

    public List<BookOrder> getAll() throws SQLException {
        return executor.executeQuery(QUERY_GET_ALL, bookOrderListHandler);
    }

    public List<BookOrder> getByUserId(int id) throws SQLException {
        return executor.executeQuery(QUERY_GET_BY_USER_ID, new PrintlnHandler(), String.valueOf(id));
    }

    public Optional<BookOrder> getById(final int id) throws SQLException {
        return executor.executeQuery(QUERY_GET_BY_ID, bookOrderOneHandler, String.valueOf(id));
    }

    /**
     * Updates BookOrder with id == newBookOrder.getId(), using fields from newBookOrder
     * @param newBookOrder
     * @return 1 (number of rows changed) if success, or throws SQLexception
     * @throws SQLException
     */
    @SuppressWarnings("JavaDoc")
    public void update(BookOrder newBookOrder) throws SQLException {
        executor.executeUpdate(QUERY_UPDATE,
                String.valueOf(newBookOrder.getBookInstanceId()),
                String.valueOf(newBookOrder.getUserOrderId()),
                String.valueOf(newBookOrder.getBookOption().toString()));
    }

    public void deleteById(final int id) throws SQLException {
        executor.executeUpdate(QUERY_DELETE, String.valueOf(id));
    }
}
