package com.epam.wl.dao.book_instance_handlers;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookInstanceOneHandler implements ResultHandler<Optional<BookInstance>> {
    private static BookInstanceOneHandler instance;

    private BookInstanceOneHandler() {
    }

    public static synchronized BookInstanceOneHandler getInstance() {
        if (instance == null)
            instance = new BookInstanceOneHandler();
        return instance;
    }

    @Override
    public Optional<BookInstance> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();

        final int id = resultSet.getInt("id");
        final int bookId = resultSet.getInt("bookid");
        final Optional<Book> oBook = BookDAO.getInstance().getById(bookId);
        return oBook.map(book -> new BookInstance(id, book));
    }
}
