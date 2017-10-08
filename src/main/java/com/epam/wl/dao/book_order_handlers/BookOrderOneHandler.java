package com.epam.wl.dao.book_order_handlers;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOptions;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookOrderOneHandler implements ResultHandler<BookOrder> {
    @Override
    public BookOrder handle(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int id = resultSet.getInt("id");
        int book_instanceid = resultSet.getInt("book_instanceid");
        int user_orderid = resultSet.getInt("user_orderid");
        BookOptions option = BookOptions.valueOf(resultSet.getString("option"));
        return new BookOrder(id, book_instanceid, user_orderid, option);
    }
}

