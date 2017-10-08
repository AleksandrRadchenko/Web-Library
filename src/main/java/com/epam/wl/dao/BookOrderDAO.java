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
    int create(
            final BookInstance bookInstance,
            final UserOrder userOrder,
            final BookOption bookOption)
            throws SQLException {
        String updateQuery = "INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES(%d, %d, '%s');";
        executor.executeUpdate(String.format(updateQuery, bookInstance.getId(), userOrder.getId(), bookOption.toString()));
        return 1;
    }

    public List<BookOrder> getAll() throws SQLException {
        final String query = "SELECT id, book_instanceid, user_orderid, option FROM book_order";
        return executor.executeQuery(query, bookOrderListHandler);
    }

    public Optional<BookOrder> getById(final int id) throws SQLException {
        final String query = "SELECT id, book_instanceid, user_orderid, option FROM book_order WHERE id=" + id;
        return executor.executeQuery(query, bookOrderOneHandler);
    }

    /**
     * Updates BookOrder with id == newBookOrder.getId(), using fields from newBookOrder
     * @param newBookOrder
     * @return 1 (number of rows changed) if success, or throws SQLexception
     * @throws SQLException
     */
    @SuppressWarnings("JavaDoc")
    public int update(BookOrder newBookOrder) throws SQLException {
        final String updateQuery = "UPDATE book_order SET book_instanceid=%d, user_orderid=%d, option='%s'";
        executor.executeUpdate(String.format(updateQuery,
                newBookOrder.getBookInstanceId(),
                newBookOrder.getOrderId(),
                newBookOrder.getBookOption().toString()));
        return 1;
    }

    public int deleteById(final int id) throws SQLException {
        final String deleteQuery = "DELETE FROM book_order WHERE id = " + id;
        executor.executeUpdate(deleteQuery);
        return 1;
    }
}
