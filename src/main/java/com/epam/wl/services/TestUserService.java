package com.epam.wl.services;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.dao.UserOrderDAO;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserRole;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.wl.enums.UserOrderStatus.IN_PROGRESS;
import static com.epam.wl.services.TestBookService.dataSource;

public class TestUserService {
    static List<User> users = new ArrayList<>(1);
    static UserDAO userDAO = new UserDAO(dataSource);
    static UserOrderDAO userOrderDAO = new UserOrderDAO(dataSource);

    public static List<User> getUser() {
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
        return users;
    }

    public static void editUser(String name, String lastName, String email, String passwordHash, UserRole userRole) {
        Optional<User> user = Optional.of(users.get(0));
        user.get().setName(name);
        user.get().setLastname(lastName);
        user.get().setEmail(email);
        user.get().setPasswordHash(passwordHash);
        user.get().setRole(userRole);
        if (user != null) {
            users.clear();
            users.add(0, user.get());
        }
    }

    public static List<UserOrder> getUserOrderBooks() {
        List<UserOrder> allBooksList = new ArrayList<>(5);//very bad!
        List<UserOrder> userBooksList = null;
        try {
            allBooksList = userOrderDAO.getUserOrderByStatus(IN_PROGRESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (allBooksList.size() > 0) {
            for (int i = 0; i < allBooksList.size(); i++) {
                if (allBooksList.get(i).getUserId() == users.get(0).getId()) {
                    userBooksList.add(allBooksList.get(i));
                }
            }
        }
        return userBooksList;
        /*
        <table border="1">
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.author}</td>
            <td> ${book.title}</td>
            <td> ${book.year}</td>
        </tr>
    </c:forEach>
</table>
         */
    }
}
