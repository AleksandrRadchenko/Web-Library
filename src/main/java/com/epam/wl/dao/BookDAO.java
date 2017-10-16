package com.epam.wl.dao;

import com.epam.wl.dao.book_handlers.*;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDAO {

    private static BookDAO instance;
    private final Executor executor = Executor.getInstance();
    private final ResultHandler<List<Book>> bookListHandler = BookListHandler.getInstance();
    private final ResultHandler<List<BookInstance>> bookInstanceListHandler = BookInstanceListHandler.getInstance();
    private final ResultHandler<Integer> bookIdHandler = BookIdHandler.getInstance();
    private final ResultHandler<Integer> bookInstanceIdHandler = BookInstanceIdHandler.getInstance();
    private final ResultHandler<List<Integer>> bookInstancesIdListHandler = BookInstancesIdListHandler.getInstance();
    private final ResultHandler<Optional<Book>> bookOneHandler = BookOneHandler.getInstance();

    private static final String GET_ALL_BOOKS_QUERY = "SELECT * FROM book";
    private static final String GET_ALL_BOOKS_INSTANCES_QUERY = "SELECT * FROM book_instance";
    private static final String GET_BOOK_ID_QUERY = "SELECT * FROM book WHERE author=? AND title=? AND year=?";
    private static final String GET_FREE_BOOK_INSTANCE_ID_QUERY = "SELECT * FROM book_instance LEFT JOIN book_order "
             + "ON book_instance.id=book_order.book_instanceid WHERE "
             + "book_instance.bookid=? AND book_instanceid IS NULL";
    private static final String ADD_NEW_BOOK_INSTANCE_QUERY = "INSERT INTO book_instance(bookid) VALUES (?)";
    private static final String ADD_NEW_BOOK_QUERY = "INSERT INTO book(author, title, year) VALUES (?, ?, ?)";
    private static final String GET_BOOK_BY_ID = "SELECT author, title, year FROM book WHERE id=?";

    public BookDAO() {
    }

    public static synchronized BookDAO getInstance() {
        if (instance == null)
            instance = new BookDAO();
        return instance;
    }

    public List<Book> getAllBooks() throws SQLException {
        return executor.executeQuery(GET_ALL_BOOKS_QUERY, bookListHandler);
    }

    public List<BookInstance> getAllBookInstances() throws SQLException {
        return executor.executeQuery(GET_ALL_BOOKS_INSTANCES_QUERY, bookInstanceListHandler);
    }

    public int getBookId(String author, String title, int year) throws SQLException {
        return executor.executeQuery(GET_BOOK_ID_QUERY, bookIdHandler, author, title, year);
    }

    public int getFreeBookInstanceId(int bookId) throws SQLException {
        return executor.executeQuery(GET_FREE_BOOK_INSTANCE_ID_QUERY, bookInstanceIdHandler, bookId);
    }

    public void addNewBookInstance(String author, String title, int year) throws SQLException {
        int bookId;

        try {
            bookId = getBookId(author, title, year);
        } catch (SQLException e) {
            if (!"No such book in database".equals(e.getMessage())) {
                throw e;
            }
            else {
                addNewBook(author, title, year);
                bookId = getBookId(author, title, year);
            }
        }

        executor.executeUpdate(ADD_NEW_BOOK_INSTANCE_QUERY, bookId);
    }

    private void addNewBook(String author, String title, int year) throws SQLException {
        executor.executeUpdate(ADD_NEW_BOOK_QUERY, author, title, year);
    }

    public List<Integer> getFreeBookInstancesForThisBook(int bookId) throws SQLException {
        return executor.executeQuery(GET_FREE_BOOK_INSTANCE_ID_QUERY, bookInstancesIdListHandler, bookId);
    }

    public Optional<Book> getById(int bookId) throws SQLException {
        return executor.executeQuery(GET_BOOK_BY_ID, bookOneHandler, bookId);
    }
}