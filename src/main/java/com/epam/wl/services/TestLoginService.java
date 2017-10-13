package com.epam.wl.services;

import com.epam.wl.DBHelper;
import com.epam.wl.dao.UserDAO;

import java.sql.SQLException;

public class TestLoginService {
    public static UserDAO userDAO = new UserDAO(DBHelper.getEmbeddedDatabase());

    public static String signIn(String email, String password) throws SQLException {
        if (userDAO.isLibrarian(email, password)) {
            return "librarian_from_login.jsp";
        }

        return "user_from_login.jsp";
    }
}
