package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log4j2
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
            log.info("UserService instance created");
        }
        log.info("UserService instance supplied");
        return instance;
    }

    public User getUser(User loggedUser) throws SQLException {
        Optional<User> user = userDAO.getUserByID(loggedUser.getId());
        user.ifPresent(user1 -> libraryUser = user1);
        return libraryUser;
    }

    public void editUser(String name, String lastName, String passwordHash) throws SQLException {
        Optional<User> user = Optional.of(libraryUser);
        user.get().setName(name);
        user.get().setLastname(lastName);
        user.get().setPasswordHash(passwordHash);
        user.get().setRole(user.get().getRole());
        userDAO.updateUser(libraryUser.getId(), name, lastName, user.get().getEmail(), passwordHash, libraryUser.getRole());
    }

    public List<UserOrder> getUserOrderBooks(User loggedUser) throws SQLException {
        return userOrderDAO.getUserOrderByUserId(loggedUser.getId());
    }
}
