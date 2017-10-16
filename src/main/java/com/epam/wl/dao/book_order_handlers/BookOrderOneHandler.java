package com.epam.wl.dao.book_order_handlers;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOption;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookOrderOneHandler implements ResultHandler<Optional<BookOrder>> {
    @Override
    public Optional<BookOrder> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();

        int id = resultSet.getInt("id");
        int bookInstanceId = resultSet.getInt("book_instanceid");
        int bookId = resultSet.getInt("bookid");
        String author = resultSet.getString("author");
        String title = resultSet.getString("title");
        int year = resultSet.getInt("year");
        int userOrderId = resultSet.getInt("user_orderid");
        int userId = resultSet.getInt("userid");
        UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        BookOption option = BookOption.valueOf(resultSet.getString("option"));

        return Optional.of(new BookOrder(id, bookInstanceId, bookId, author, title,
                year, userOrderId, userId, status, name, lastName, email, option));
    }
}

