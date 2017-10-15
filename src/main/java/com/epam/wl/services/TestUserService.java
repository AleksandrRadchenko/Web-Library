package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.dao.UserDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.db.JdbcConnector;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserRole;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.wl.enums.UserOrderStatus.IN_PROGRESS;

public class TestUserService {
//    static UserDAO userDAO = new UserDAO(dataSource);
//    static UserOrderDAO userOrderDAO = new UserOrderDAO(dataSource);

    private static TestUserService instance;
    private static DataSource dataSource = JdbcConnector.getDataSource();

    private UserOrderDAO userOrderDAO;
    private BookDAO bookDAO;
    private UserDAO userDAO;
    private static List<User> users = new ArrayList<>(1);
    private static List<UserOrder> booksList = new ArrayList<>();

    private TestUserService() {
    }

    public static synchronized TestUserService getInstance() {
        if (instance == null) {
            instance = new TestUserService();
            instance.userDAO = new UserDAO(dataSource);
            instance.userOrderDAO = new UserOrderDAO(dataSource);
            instance.bookDAO = new BookDAO(dataSource);
        }
        return instance;
    }

    public List<User> getUser() {
        Optional<User> user = null;//new ArrayList<>(1);
        try {
            user = userDAO.getUserByID(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            //users.clear();
            users.add(user.get());
            if (users.size() > 1) {
                users.remove(1);
            }
        }
//        users.add(new User(1, "Ivan", "Sid",
//                "dede", "spsps", UserRole.USER));
        return users;
    }

    public void editUser(String name, String lastName, String email, String passwordHash) {//, UserRole userRole
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
    }

    public List<UserOrder> getUserOrderBooks() {
        //List<UserOrder> booksList = new ArrayList<>();//very bad!
        try {
            booksList = userOrderDAO.getUserOrderByUserId(1);//users.get(0).getId()
            booksList.addAll(userOrderDAO.getUserOrderByUserId(1));//.add((UserOrder) userOrderDAO.getUserOrderByUserId(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        booksList.add(new UserOrder(1, new User(1, "Ivan", "Sid",
                "dede", "spsps", UserRole.USER),
                new Book(1, "T", "a", 1899),
                IN_PROGRESS));
        return booksList;
    }
}
