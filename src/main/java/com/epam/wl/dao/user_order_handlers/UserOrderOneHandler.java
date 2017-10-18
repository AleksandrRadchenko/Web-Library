package com.epam.wl.dao.user_order_handlers;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.dao.UserDAO;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.User;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserOrderOneHandler implements ResultHandler<Optional<UserOrder>> {

    private static UserOrderOneHandler instance;

    private UserOrderOneHandler() {
    }

    public static synchronized UserOrderOneHandler getInstance() {
        if (instance == null)
            instance = new UserOrderOneHandler();
        return instance;
    }

    @Override
    public Optional<UserOrder> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        final int id = resultSet.getInt("user_order_id");
        final int bookId = resultSet.getInt("book_id");
        final int userId = resultSet.getInt("user_id");
        final UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
        final Book book = BookDAO.getInstance().getById(bookId).get(); // TODO: 16.10.2017 optional
        final User user = UserDAO.getInstance().getUserByID(userId).get(); // TODO: 16.10.2017
        return Optional.of(new UserOrder(id, user, book, status));
    }
}
