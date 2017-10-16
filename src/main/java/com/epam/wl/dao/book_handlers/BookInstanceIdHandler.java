package com.epam.wl.dao.book_handlers;

import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInstanceIdHandler implements ResultHandler<Integer> {
    private static BookInstanceIdHandler instance;

    private BookInstanceIdHandler() {
    }

    public static synchronized BookInstanceIdHandler getInstance() {
        if (instance == null)
            instance = new BookInstanceIdHandler();
        return instance;
    }
    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("book_instance.id");
        }

        throw new SQLException("No free book instance in library");
    }
}
