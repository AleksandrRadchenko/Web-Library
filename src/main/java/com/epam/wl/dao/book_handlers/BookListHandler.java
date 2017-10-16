package com.epam.wl.dao.book_handlers;

import com.epam.wl.entities.Book;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookListHandler implements ResultHandler<List<Book>> {
    private static BookListHandler instance;

    private BookListHandler() {
    }

    public static synchronized BookListHandler getInstance() {
        if (instance == null)
            instance = new BookListHandler();
        return instance;
    }
    @Override
    public List<Book> handle(ResultSet resultSet) throws SQLException {
        final List<Book> books = new ArrayList<>();

        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt("id"), resultSet.getString("author"),
                    resultSet.getString("title"), resultSet.getInt("year")));
        }

        return books;
    }
}
