package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class LoginService {
    private static LoginService instance;
    private final UserDAO userDAO = UserDAO.getInstance();

    private LoginService() {
    }

    public static synchronized LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public String getRolePage(String email) throws SQLException {
        if (userDAO.isLibrarian(email)) {
            return "/librarian";
        }

        return "/userprofile";
    }

    public String addNewUser(String name, String lastName, String email, String password, UserRole userRole,
                             String passwordRepeat, String captcha) throws SQLException {
        if (!password.equals(passwordRepeat)) {
            return "errors/passwords_error.html";
        }

        if (!"W68HP".equals(captcha)) {
            return "errors/captcha_error.html";
        }

        Optional<User> user;
        user = userDAO.getUserByEmail(email);

        if (user.isPresent()) {
            return "errors/user_already_exists.html";
        }
        userDAO.addUser(name, lastName, email, password, userRole);
        return "login.jsp";
    }

    public String confirmUser(HttpServletRequest request) throws SQLException {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        Optional<User> userOptional = userDAO.getUserByEmailAndPassword(email, password);

        if (userOptional.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser", userOptional.get());

            return getRolePage(email);
        } else {
            return "errors/login_error.html";
        }
    }
}
