package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.db.JdbcConnector;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserRole;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUserService {

    private static TestUserService instance;
    private static DataSource dataSource = JdbcConnector.getDataSource();

    private UserOrderDAO userOrderDAO;
    private UserDAO userDAO;
    private static List<User> users = new ArrayList<>(1);

    private TestUserService() {
    }

    public static synchronized TestUserService getInstance() {
        if (instance == null) {
            instance = new TestUserService();
            instance.userDAO = new UserDAO(dataSource);
            instance.userOrderDAO = new UserOrderDAO(dataSource);
        }
        return instance;
    }

    public List<User> getUser() {
        Optional<User> user = null;        try {
            user = userDAO.getUserByID(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            users.add(user.get());
            if (users.size() > 1) {
                users.remove(1);
            }
        }
        return users;
    }

    public void editUser(String name, String lastName, String email, String passwordHash) {
        Optional<User> user = Optional.of(users.get(0));
        user.get().setName(name);
        user.get().setLastname(lastName);
        user.get().setEmail(email);
        user.get().setPasswordHash(passwordHash);
        user.get().setRole(UserRole.USER);//users.get(0).getRole()
        if (user != null) {
            users.clear();
            users.add(0, user.get());
        }
        try {
            userDAO.updateUser(users.get(0).getId(), name, lastName, email, passwordHash, users.get(0).getRole());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserOrder> getUserOrderBooks() {
        List<UserOrder> bookList = new ArrayList<>();
        try {
            bookList = userOrderDAO.getUserOrderByUserId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
