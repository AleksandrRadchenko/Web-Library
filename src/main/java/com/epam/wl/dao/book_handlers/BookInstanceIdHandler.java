package com.epam.wl.dao.book_handlers;

import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInstanceIdHandler implements ResultHandler<Integer> {
    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("book_instance.id");
        }

        throw new SQLException("No free book instance in library");
    }
}
