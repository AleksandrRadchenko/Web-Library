package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.entities.Book;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.List;

@Log4j2
public class BookService {
    private static BookService instance;
    private final BookDAO bookDAO = BookDAO.getInstance();

    private BookService() {
    }

    public static synchronized BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
            log.info("BookService instance created");
        }
        log.info("BookService instance supplied");
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