package com.epam.wl.services;

import com.epam.wl.dao.BookDAO;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public List<Book> getBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public List<Book> getBookByAuthor(String author) throws SQLException {
        return bookDAO.getBooksByAuthor(author);
    }

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