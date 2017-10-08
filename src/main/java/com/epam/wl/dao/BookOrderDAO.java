package com.epam.wl.dao;

import com.epam.wl.dao.book_order_handlers.*;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.BookOptions;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@RequiredArgsConstructor
public class BookOrderDAO {
    private final DataSource dataSource;
    private final ResultHandler<BookOrder> bookOrderOneHandler = new BookOrderOneHandler();
    private final ResultHandler<List<BookOrder>> bookOrderListHandler = new BookOrderListHandler();


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
            BookOrderListHandler h = new BookOrderListHandler();
            return h.handle(st.getResultSet());
        }
    }

    public BookOrder getById(final int id) throws SQLException {
        BookOrder result;
        try (Connection connection = dataSource.getConnection()) {
            Executor ex = new Executor(connection);
            String query = "SELECT id, book_instanceid, user_orderid, option FROM book_order WHERE id=" + id;
            return ex.executeQuery(query, bookOrderOneHandler);
        }
    }

    static void printResultSet(ResultSet set) throws SQLException {
        int row = 1;
        while (set.next()) {
            System.out.print(row++ + " : ");
            ResultSetMetaData metaData = set.getMetaData();
            for (int i = 1; i < metaData.getColumnCount(); i++) {
                Object o = set.getObject(i);
                System.out.print(metaData.getColumnName(i) + " = " + o.toString() + "; ");
            }
            int lastColumn = metaData.getColumnCount();
            System.out.println(metaData.getColumnName(lastColumn) + " = " + set.getObject(lastColumn));
        }
    }
}
