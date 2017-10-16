package com.epam.wl.dao.user_handlers;

import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserOneHandler implements ResultHandler<Optional<User>> {
    private static UserOneHandler instance;

    private UserOneHandler() {
    }

    public static synchronized UserOneHandler getInstance() {
        if (instance == null)
            instance = new UserOneHandler();
        return instance;
    }

    @Override
    public Optional<User> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        int userID = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String lastname = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        String passwordhash = resultSet.getString("passwordhash");
        UserRole role = UserRole.valueOf(resultSet.getString("role"));
        return Optional.of(new User(userID, name, lastname, email, passwordhash, role));
    }
}
