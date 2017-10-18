package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.entities.Book;
import java.sql.SQLException;
import java.util.List;

/**
 * BookService is used to interact with BookDAO and calling servlets to provide the required operations
 */
public class BookService {
    private static BookService instance;
    private final BookDAO bookDAO = BookDAO.getInstance();

    private BookService() {
    }

    public static synchronized BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    /**
     * Method is used for getting all Books from BookDAO
     * @return list of Book
     * @throws SQLException
     */
    public List<Book> getBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    /**
     * Method is used for getting all Books from BookDAO with required Book.author
     * @param author
     * @return list of Book
     * @throws SQLException
     */
    public List<Book> getBookByAuthor(String author) throws SQLException {
        return bookDAO.getBooksByAuthor(author);
    }

    /**
     * Method is used for getting all Books from BookDAO with required Book.title
     * @param title
     * @return list of Book
     * @throws SQLException
     */
    public List<Book> getBooksByTitle(String title) throws SQLException {
        return bookDAO.getBooksByTitle(title);
    }
}