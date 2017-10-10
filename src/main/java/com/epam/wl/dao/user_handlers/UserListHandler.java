package com.epam.wl.dao.user_handlers;

import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListHandler implements ResultHandler<List<User>> {
    @Override
    public List<User> handle(ResultSet resultSet) throws SQLException {
        List<User> resultUserList = new ArrayList();
        while (resultSet.next()) {
            int userID = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String lastname = resultSet.getString("lastname");
            String email = resultSet.getString("email");
            String passwordhash = resultSet.getString("passwordhash");
            UserRole role = UserRole.valueOf(resultSet.getString("role"));
            resultUserList.add(new User(userID, name, lastname, email, passwordhash, role));
        }
        return resultUserList;
    }
}
