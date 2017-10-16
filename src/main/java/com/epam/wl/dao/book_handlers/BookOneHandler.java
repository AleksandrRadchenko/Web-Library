package com.epam.wl.dao.book_handlers;

import com.epam.wl.entities.Book;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookOneHandler implements ResultHandler<Optional<Book>> {
    private static BookOneHandler instance;

    private BookOneHandler() {
    }

    public static synchronized BookOneHandler getInstance() {
        if (instance == null)
            instance = new BookOneHandler();
        return instance;
    }

    @Override
    public Optional<Book> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        int id = resultSet.getInt("id");
        String author = resultSet.getString("author");
        String title = resultSet.getString("title");
        int year = resultSet.getInt("year");
        return Optional.of(new Book(id, author, title, year));
    }
}
