package com.epam.wl.dao.user_order_handlers;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.enums.UserRole;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserOrderOneHandler implements ResultHandler<Optional<UserOrder>> {
    @Override
    public Optional<UserOrder> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        final int userOrderID = resultSet.getInt("user_order.id");
        final int userID = resultSet.getInt("user.id");
        final String userName = resultSet.getString("user.name");
        final String userLastname = resultSet.getString("user.lastname");
        final String userEmail = resultSet.getString("user.email");
        final int bookId = resultSet.getInt("book_id");
        final String bookTitle = resultSet.getString("book.title");
        final  String bookAuthor = resultSet.getString("book.author");
        final int bookYear = resultSet.getInt("book.year");
        final UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("user_order.status"));
        final User user = new User(userID, userName, userLastname, userEmail, "", UserRole.USER);
        final Book book = new Book(bookId, bookTitle, bookAuthor, bookYear);
        return Optional.of(new UserOrder(userOrderID, user, book, status));
    }
}
