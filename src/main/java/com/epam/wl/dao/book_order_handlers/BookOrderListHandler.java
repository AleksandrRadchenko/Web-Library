package com.epam.wl.dao.book_order_handlers;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOption;
import com.epam.wl.enums.UserOrderStatus;
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

            BookOrder bookOrder = new BookOrder(id, bookInstanceId, bookId, author, title,
                    year, userOrderId, userId, status, name, lastName, email, option);
            output.add(bookOrder);
        }

        return output;
    }
}

