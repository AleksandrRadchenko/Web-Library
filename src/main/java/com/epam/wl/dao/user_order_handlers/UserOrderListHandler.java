package com.epam.wl.dao.user_order_handlers;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.enums.UserRole;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserOrderListHandler implements ResultHandler<List<UserOrder>> {
    private static UserOrderListHandler instance;

    private UserOrderListHandler() {
    }

    public static synchronized UserOrderListHandler getInstance() {
        if (instance == null)
            instance = new UserOrderListHandler();
        return instance;
    }

    @Override
    public List<UserOrder> handle(ResultSet resultSet) throws SQLException {
        List<UserOrder> resultUserOrderList = new ArrayList();
        while (resultSet.next()) {
            final int userOrderID = resultSet.getInt("user_order_id");
            final int userID = resultSet.getInt("user_id");
            final String userName = resultSet.getString("user_name");
            final String userLastname = resultSet.getString("user_lastname");
            final String userEmail = resultSet.getString("user_email");
            final int bookId = resultSet.getInt("book_id");
            final String bookTitle = resultSet.getString("title");
            final String bookAuthor = resultSet.getString("author");
            final int bookYear = resultSet.getInt("year");
            final UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
            final User user = new User(userID, userName, userLastname, userEmail, "", UserRole.USER);
            final Book book = new Book(bookId, bookTitle, bookAuthor, bookYear);
            resultUserOrderList.add(new UserOrder(userOrderID, user, book, status));
        }
        return resultUserOrderList;
    }
}