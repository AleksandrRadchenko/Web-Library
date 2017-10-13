package com.epam.wl.services;

import com.epam.wl.DBHelper;
import com.epam.wl.dao.UserDAO;
import com.epam.wl.enums.UserRole;

import java.sql.SQLException;

public class TestLoginService {
    public static UserDAO userDAO = new UserDAO(DBHelper.getEmbeddedDatabase());

    public static String signIn(String email, String password) throws SQLException {
        if (userDAO.isLibrarian(email, password)) {
            return "librarian_from_login.jsp";
        }

        return "user_from_login.jsp";
    }

    public static void addNewUser(String name, String lastname, String email, String passwordHash, UserRole userRole) {
        try {
            userDAO.addUser(name, lastname, email, passwordHash, userRole);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
