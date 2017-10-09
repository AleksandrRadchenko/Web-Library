package com.epam.wl.dao;

import com.epam.wl.dao.book_order_handlers.BookOrderListHandler;
import com.epam.wl.dao.book_order_handlers.BookOrderOneHandler;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.UserOrder;
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

    private static final String QUERY_CREATE = "INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES(%d, %d, '%s');";
    private static final String QUERY_GET_ALL = "SELECT id, book_instanceid, user_orderid, option FROM book_order";
    private static final String QUERY_GET_BY_ID = "SELECT id, book_instanceid, user_orderid, option FROM book_order WHERE id=";
    private static final String QUERY_UPDATE = "UPDATE book_order SET book_instanceid=%d, user_orderid=%d, option='%s'";
    private static final String QUERY_DELETE = "DELETE FROM book_order WHERE id = ";

    public BookOrderDAO(DataSource dataSource) {
        executor = new Executor(dataSource);
    }

    /**
     * Create row in book_order table
     * @param bookInstance
     * @param userOrder
     * @param bookOption
     * @return 1 (number of rows changed) if success, or throws SQLexception
     * @throws SQLException
     */
    @SuppressWarnings("JavaDoc")
    public void create(
            final BookInstance bookInstance,
            final UserOrder userOrder,
            final BookOption bookOption)
            throws SQLException {
        executor.executeUpdate(String.format(QUERY_CREATE, bookInstance.getId(), userOrder.getId(), bookOption.toString()));
    }

    public List<BookOrder> getAll() throws SQLException {
        return executor.executeQuery(QUERY_GET_ALL, bookOrderListHandler);
    }

    public Optional<BookOrder> getById(final int id) throws SQLException {
        return executor.executeQuery(QUERY_GET_BY_ID + id, bookOrderOneHandler);
    }

    /**
     * Updates BookOrder with id == newBookOrder.getId(), using fields from newBookOrder
     * @param newBookOrder
     * @return 1 (number of rows changed) if success, or throws SQLexception
     * @throws SQLException
     */
    @SuppressWarnings("JavaDoc")
    public void update(BookOrder newBookOrder) throws SQLException {
        executor.executeUpdate(String.format(QUERY_UPDATE,
                newBookOrder.getBookInstanceId(),
                newBookOrder.getOrderId(),
                newBookOrder.getBookOption().toString()));
    }

    public void deleteById(final int id) throws SQLException {
        executor.executeUpdate(QUERY_DELETE + id);
    }
}
