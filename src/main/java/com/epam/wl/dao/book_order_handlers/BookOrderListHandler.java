package com.epam.wl.dao.book_order_handlers;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOption;
import com.epam.wl.executor.ResultHandler;
import com.epam.wl.services.BookInstanceService;
import com.epam.wl.services.UserOrderService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookOrderListHandler implements ResultHandler<List<BookOrder>> {
    private static BookOrderListHandler instance;

    private BookOrderListHandler() {
    }

    public static synchronized BookOrderListHandler getInstance() {
        if (instance == null)
            instance = new BookOrderListHandler();
        return instance;
    }

    @Override
    public List<BookOrder> handle(ResultSet resultSet) throws SQLException {
        List<BookOrder> output = new ArrayList<BookOrder>();
        while (resultSet.next()) {
            int id = resultSet.getInt("book_order_id");
            int book_instanceid = resultSet.getInt("book_instanceid");
            int user_orderid = resultSet.getInt("user_orderid");
            BookOption option = BookOption.valueOf(resultSet.getString("option"));
            BookOrder bookOrder = new BookOrder(id, BookInstanceService.getInstance().getById(book_instanceid),
                    UserOrderService.getInstance().getById(user_orderid), option);
            output.add(bookOrder);
        }
        return output;
    }
}

