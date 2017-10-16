package com.epam.wl.dao.book_handlers;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookInstanceListHandler implements ResultHandler<List<BookInstance>> {
    private static BookInstanceListHandler instance;

    private BookInstanceListHandler() {
    }

    public static synchronized BookInstanceListHandler getInstance() {
        if (instance == null)
            instance = new BookInstanceListHandler();
        return instance;
    }
    @Override
    public List<BookInstance> handle(ResultSet resultSet) throws SQLException {
        final List<BookInstance> bookInstances = new ArrayList<>();

        while (resultSet.next()) {
            int bookId = resultSet.getInt("bookid");
            Optional<Book> oBook = BookDAO.getInstance().getById(bookId);
            if (!oBook.isPresent()) throw new SQLException("There is no Book for id = " + bookId);
            else bookInstances.add(new BookInstance(resultSet.getInt("id"), oBook.get()));
        }

        return bookInstances;
    }
}
