package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.db.JdbcConnector;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserRole;
import com.epam.wl.servlets.UserServlet;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUserService {

    private static TestUserService instance;
    private static List<User> users = new ArrayList<>(1);
    private final UserOrderDAO userOrderDAO = UserOrderDAO.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();

    private TestUserService() {
    }

    public static synchronized TestUserService getInstance() {
        if (instance == null) {
            instance = new TestUserService();
        }
        return instance;
    }

    public List<User> getUser(User loggedUser) throws SQLException {
        Optional<User> user = userDAO.getUserByID(loggedUser.getId());
        if (user.isPresent()) {
            users.add(user.get());
            if (users.size() > 1) {
                users.remove(1);
            }
        }
        return users;
    }

    public void editUser(String name, String lastName, String email, String passwordHash) throws SQLException {
        Optional<User> user = Optional.of(users.get(0));
        user.get().setName(name);
        user.get().setLastname(lastName);
        user.get().setEmail(email);
        user.get().setPasswordHash(passwordHash);
        user.get().setRole(user.get().getRole());
        users.clear();
        users.add(0, user.get());
        userDAO.updateUser(users.get(0).getId(), name, lastName, email, passwordHash, users.get(0).getRole());
    }

    public List<UserOrder> getUserOrderBooks(User loggedUser) throws SQLException {
        return userOrderDAO.getUserOrderByUserId(loggedUser.getId());
    }
}
