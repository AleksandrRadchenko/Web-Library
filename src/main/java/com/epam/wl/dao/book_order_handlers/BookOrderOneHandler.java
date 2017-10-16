package com.epam.wl.dao.book_order_handlers;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOption;
import com.epam.wl.executor.ResultHandler;
import com.epam.wl.services.BookInstanceService;
import com.epam.wl.services.UserOrderService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookOrderOneHandler implements ResultHandler<Optional<BookOrder>> {
    private static BookOrderOneHandler instance;

    private BookOrderOneHandler() {
    }

    public static synchronized BookOrderOneHandler getInstance() {
        if (instance == null)
            instance = new BookOrderOneHandler();
        return instance;
    }

    @Override
    public Optional<BookOrder> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        int id = resultSet.getInt("book_order_id");
        int book_instanceid = resultSet.getInt("book_instanceid");
        int user_orderid = resultSet.getInt("user_orderid");
        BookOption option = BookOption.valueOf(resultSet.getString("option"));
        return Optional.of(new BookOrder(id, BookInstanceService.getInstance().getById(book_instanceid),
                UserOrderService.getInstance().getById(user_orderid), option));
    }
}

