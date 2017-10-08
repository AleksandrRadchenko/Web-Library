package com.epam.wl.dao.book_order_handlers;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOptions;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookOrderListHandler implements ResultHandler<List<BookOrder>> {
    @Override
    public List<BookOrder> handle(ResultSet resultSet) throws SQLException {
        List<BookOrder> output = new ArrayList<BookOrder>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int book_instanceid = resultSet.getInt("book_instanceid");
            int user_orderid = resultSet.getInt("user_orderid");
            BookOptions option = BookOptions.valueOf(resultSet.getString("option"));
            BookOrder bookOrder = new BookOrder(id, book_instanceid, user_orderid, option);
            output.add(bookOrder);
        }
        return output;
    }
}

