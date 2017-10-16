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
    @Override
    public List<UserOrder> handle(ResultSet resultSet) throws SQLException {
        List<UserOrder> resultUserOrderList = new ArrayList();
        while (resultSet.next()) {
            int userOrderID = resultSet.getInt("user_order_id");
            int userID = resultSet.getInt("user_id");
            String userName = resultSet.getString("user_name");
            String userLastname = resultSet.getString("user_lastname");
            String userEmail = resultSet.getString("user_email");
            int bookId = resultSet.getInt("book_id");
            String bookTitle = resultSet.getString("book_title");//без book_
            String bookAuthor = resultSet.getString("book_author");//без book_
            int bookYear = resultSet.getInt("book_year");//без book_
            UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
            User user = new User(userID, userName, userLastname, userEmail, "", UserRole.USER);
            Book book = new Book(bookId, bookTitle, bookAuthor, bookYear);
            resultUserOrderList.add(new UserOrder(userOrderID, user, book, status));
        }
        return resultUserOrderList;
    }
}