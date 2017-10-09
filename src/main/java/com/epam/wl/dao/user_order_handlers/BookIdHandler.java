package com.epam.wl.dao.user_order_handlers;

import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookIdHandler implements ResultHandler<Integer> {
    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
        if (resultSet.first()) {
            return resultSet.getInt("id");
        }

        throw new NullPointerException("No such book in database");
    }
}
