package com.epam.wl.dao.user_handlers;

import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIsLibrarianHandler implements ResultHandler<Boolean> {
    private static UserIsLibrarianHandler instance;

    private UserIsLibrarianHandler() {
    }

    public static synchronized UserIsLibrarianHandler getInstance() {
        if (instance == null)
            instance = new UserIsLibrarianHandler();
        return instance;
    }

    @Override
    public Boolean handle(ResultSet resultSet) throws SQLException {
        if (resultSet.first()) {
            return "LIBRARIAN".equals(resultSet.getString("role"));
        }

        throw new SQLException("No such user in database");
    }
}
