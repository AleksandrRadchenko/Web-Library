package com.epam.wl.dao.book_handlers;

import com.epam.wl.entities.BookInstance;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInstanceListHandler implements ResultHandler<List<BookInstance>> {
    @Override
    public List<BookInstance> handle(ResultSet resultSet) throws SQLException {
        final List<BookInstance> bookInstances = new ArrayList<>();

        while (resultSet.next()) {
            bookInstances.add(new BookInstance(resultSet.getInt("id"),
                    resultSet.getInt("bookid")));
        }

        return bookInstances;
    }
}
