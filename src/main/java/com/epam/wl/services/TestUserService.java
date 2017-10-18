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
    private static User libraryUser = new User();
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

    public User getUser(User loggedUser) throws SQLException {
        Optional<User> user = userDAO.getUserByID(loggedUser.getId());
        user.ifPresent(user1 -> libraryUser = user1);
        return libraryUser;
    }

    public void editUser(String name, String lastName, String email, String passwordHash) throws SQLException {
        Optional<User> user = Optional.of(libraryUser);//users.get(0)
        user.get().setName(name);
        user.get().setLastname(lastName);
        user.get().setEmail(email);
        user.get().setPasswordHash(passwordHash);
        user.get().setRole(user.get().getRole());
        userDAO.updateUser(libraryUser.getId(), name, lastName, email, passwordHash, libraryUser.getRole());
    }

    public List<UserOrder> getUserOrderBooks(User loggedUser) throws SQLException {
        return userOrderDAO.getUserOrderByUserId(loggedUser.getId());
    }
}
