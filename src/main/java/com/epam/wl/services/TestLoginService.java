package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class TestLoginService {
    private static TestLoginService instance;
    private final UserDAO userDAO = UserDAO.getInstance();

    private TestLoginService() {
    }

    public static synchronized TestLoginService getInstance() {
        if (instance == null) {
            instance = new TestLoginService();
        }
        return instance;
    }

    public String getRolePage(String email) throws SQLException {
        if (userDAO.isLibrarian(email)) {
            return "/librarian";
        }

        return "/userprofile";//users.jsp
    }

    public String addNewUser(String name, String lastName, String email, String password, UserRole userRole,
                             String passwordRepeat, String captcha) {
        if (!password.equals(passwordRepeat)) {
            return "errors/passwords_error.html";
        }

        if (!"W68HP".equals(captcha)) {
            return "errors/captcha_error.html";
        }

        Optional<User> user = Optional.empty();

        try {
            user = userDAO.getUserByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user.isPresent()) {
            return "errors/user_already_exists.html";
        }

        try {
            userDAO.addUser(name, lastName, email, password, userRole);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "login.jsp";
    }

    public String confirmUser(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        try {
            Optional<User> userOptional = userDAO.getUserByEmailAndPassword(email, password);

            if (userOptional.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", userOptional.get());

                return getRolePage(email);
            } else {
                return "errors/login_error.html";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "errors/login_error.html";
    }
}
