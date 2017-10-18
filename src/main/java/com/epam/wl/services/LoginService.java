package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

@Log4j2
public class LoginService {
    private static LoginService instance;
    private final UserDAO userDAO = UserDAO.getInstance();

    private LoginService() {
    }

    public static synchronized LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
            log.info("LoginService instance created");
        }
        log.info("LoginService instance supplied");
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
            log.info("Password error for user {}", email);
            return "errors/passwords_error.html";
        }

        if (!"W68HP".equals(captcha)) {
            log.info("Captcha error for user {}", email);
            return "errors/captcha_error.html";
        }

        Optional<User> user;
        user = userDAO.getUserByEmail(email);

        if (user.isPresent()) {
            log.info("User email already exists {}", email);
            return "errors/user_already_exists.html";
        }
        userDAO.addUser(name, lastName, email, password, userRole);
        log.info("New user added {}", email);
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
