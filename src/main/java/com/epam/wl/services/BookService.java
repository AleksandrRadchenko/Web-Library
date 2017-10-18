package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.entities.Book;
import lombok.extern.log4j.Log4j2;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * BookService is used to interact with BookDAO and calling servlets to provide the required operations
 */
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

    private List<Integer> getFreeBookInstancesForThisBook(int bookId) throws SQLException{
        return bookDAO.getFreeBookInstancesForThisBook(bookId);
    }

    public Map<Book, List<Integer>> getBookAndFreeBookInstanceMap(List<Book> bookList) throws SQLException {
        final Map<Book, List<Integer>> resultMap = new TreeMap<>(Comparator.comparingInt(Book::getId));

        for (Book book : bookList) {
            resultMap.put(book, getFreeBookInstancesForThisBook(book.getId()));
        }
        return resultMap;
    }
}