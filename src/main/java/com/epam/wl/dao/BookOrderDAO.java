package com.epam.wl.dao;

import com.epam.wl.dao.book_order_handlers.BookOrderListHandler;
import com.epam.wl.dao.book_order_handlers.BookOrderOneHandler;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.BookOptions;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class BookOrderDAO {
    private final DataSource dataSource;
    private Executor executor;
    private final ResultHandler<BookOrder> bookOrderOneHandler = new BookOrderOneHandler();
    private final ResultHandler<List<BookOrder>> bookOrderListHandler = new BookOrderListHandler();

    public BookOrderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        executor = new Executor(dataSource);
        ;
    }

    /**
     * Create row and returns 1 (number of rows changed) or throws SQLException
     */
    int create(
            final BookInstance bookInstance,
            final UserOrder userOrder,
            final BookOptions bookOption)
            throws SQLException {
        String updateQuery = "INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES(%d, %d, '%s');";
        executor.executeUpdate(String.format(updateQuery, bookInstance.getId(), userOrder.getId(), bookOption.toString()));
        return 1;
    }


    public List<BookOrder> getAll() throws SQLException {
        final String query = "SELECT id, book_instanceid, user_orderid, option FROM book_order";
        return executor.executeQuery(query, bookOrderListHandler);
    }

    public BookOrder getById(final int id) throws SQLException {
        final String query = "SELECT id, book_instanceid, user_orderid, option FROM book_order WHERE id=" + id;
        return executor.executeQuery(query, bookOrderOneHandler);
    }
}
