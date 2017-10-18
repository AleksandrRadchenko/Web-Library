package com.epam.wl.dao;

import com.epam.wl.dao.book_handlers.*;
import com.epam.wl.entities.Book;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDAO {

    private static BookDAO instance;
    private final Executor executor = Executor.getInstance();
    private final ResultHandler<List<Book>> bookListHandler = BookListHandler.getInstance();
    private final ResultHandler<Integer> bookInstanceIdHandler = BookInstanceIdHandler.getInstance();
    private final ResultHandler<List<Integer>> bookInstancesIdListHandler = BookInstancesIdListHandler.getInstance();
    private final ResultHandler<Optional<Book>> bookOneHandler = BookOneHandler.getInstance();

    private static final String GET_ALL_BOOKS_QUERY = "SELECT * FROM book";
    private static final String GET_FREE_BOOK_INSTANCE_ID_QUERY = "SELECT book_instance.id AS book_instance_id " +
            "FROM book_instance LEFT JOIN book_order "
            + "ON book_instance.id=book_order.book_instanceid WHERE "
            + "book_instance.bookid=? AND book_instanceid IS NULL";
    private static final String ADD_NEW_BOOK_QUERY = "INSERT INTO book(author, title, year) VALUES (?, ?, ?)";
    private static final String GET_BOOK_BY_ID = "SELECT id, author, title, year FROM book WHERE id=?";
    public static final String GET_BOOKS_BY_AUTHOR = "SELECT * FROM book WHERE author LIKE ?;";
    public static final String GET_BOOKS_BY_TITLE = "SELECT * FROM book WHERE title LIKE ?;";

    private BookDAO() {
    }

    public static synchronized BookDAO getInstance() {
        if (instance == null)
            instance = new BookDAO();
        return instance;
    }

    public List<Book> getAllBooks() throws SQLException {
        return executor.executeQuery(GET_ALL_BOOKS_QUERY, bookListHandler);
    }

    public int getFreeBookInstanceId(int bookId) throws SQLException {
        return executor.executeQuery(GET_FREE_BOOK_INSTANCE_ID_QUERY, bookInstanceIdHandler, bookId);
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

    public List<Book> getBooksByAuthor(String author) throws SQLException {
        return executor.executeQuery(GET_BOOKS_BY_AUTHOR, bookListHandler, "%" + author + "%");
    }

    public List<Book> getBooksByTitle(String title) throws SQLException {
        return executor.executeQuery(GET_BOOKS_BY_TITLE, bookListHandler, "%" + title + "%");
    }
}