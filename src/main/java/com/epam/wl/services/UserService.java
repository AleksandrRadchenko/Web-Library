package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static UserService instance;
    private static User libraryUser = new User();
    private final UserOrderDAO userOrderDAO = UserOrderDAO.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();

    private UserService() {
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User getUser(User loggedUser) throws SQLException {
        Optional<User> user = userDAO.getUserByID(loggedUser.getId());
        user.ifPresent(user1 -> libraryUser = user1);
        return libraryUser;
    }

    public void editUser(String name, String lastName, String email, String passwordHash) throws SQLException {
        Optional<User> user = Optional.of(libraryUser);
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
