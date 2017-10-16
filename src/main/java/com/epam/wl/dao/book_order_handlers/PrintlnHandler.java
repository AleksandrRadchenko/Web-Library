package com.epam.wl.dao.book_order_handlers;

import com.epam.wl.DBHelper;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintlnHandler implements ResultHandler<List<BookOrder>> {
    @Override
    public List<BookOrder> handle(ResultSet resultSet) throws SQLException {
        DBHelper.printResultSet(resultSet);
        return new ArrayList<BookOrder>();
    }
}


