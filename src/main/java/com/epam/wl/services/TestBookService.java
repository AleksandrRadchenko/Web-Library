package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.entities.Book;
import java.sql.SQLException;
import java.util.List;

public class TestBookService {
    private static TestBookService instance;
    private final BookDAO bookDAO = BookDAO.getInstance();

    private TestBookService() {
    }

    public static synchronized TestBookService getInstance() {
        if (instance == null) {
            instance = new TestBookService();
        }
        return instance;
    }

    public List<Book> getBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public List<Book> getBookByAuthor(String author) throws SQLException {
        return bookDAO.getBooksByAuthor(author);
    }

    public List<Book> getBooksByTitle(String title) throws SQLException {
        return bookDAO.getBooksByTitle(title);
    }
}