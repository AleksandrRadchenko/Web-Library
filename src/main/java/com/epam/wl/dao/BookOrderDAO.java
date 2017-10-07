package com.epam.wl.dao;

import com.epam.wl.dao.book_order_handlers.BookOrderHandler;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.BookOptions;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RequiredArgsConstructor
public class BookOrderDAO {
    private final DataSource dataSource;

    /**
     * Create row and returns 1 (number of rows changed)
     */
    int create(final BookInstance bookInstance, final UserOrder userOrder, final BookOptions bookOption) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, bookInstance.getId());
            preparedStatement.setInt(2, userOrder.getId());
            preparedStatement.setString(3, bookOption.toString());
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // In case of exception, no rows was changed
    }


    public List<BookOrder> getAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement st = connection.createStatement();
            st.executeQuery("SELECT id, book_instanceid, user_orderid, option FROM book_order");
            BookOrderHandler h = new BookOrderHandler();
            return h.handle(st.getResultSet());
        }
    }

    public BookOrder getById(final int id) {
        BookOrder result = null;
//        ResultHandler<BookOrder> handler = new ResultHandler
//        executor.execQuery("SELECT * from BookOrder WHERE id = " + id);

        return result;
    }
}