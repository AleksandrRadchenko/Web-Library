package com.epam.wl.dao.book_handlers;

import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookIdHandler implements ResultHandler<Integer> {
    private static BookIdHandler instance;

    private BookIdHandler() {
    }

    public static synchronized BookIdHandler getInstance() {
        if (instance == null)
            instance = new BookIdHandler();
        return instance;
    }

    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        if (resultSet.first()) {
            return resultSet.getInt("id");
        }

        throw new SQLException("No such book in database");
    }
}
