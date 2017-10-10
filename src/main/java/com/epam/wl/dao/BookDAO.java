package com.epam.wl.dao;

import com.epam.wl.dao.book_handlers.BookIdHandler;
import com.epam.wl.dao.book_handlers.BookInstanceIdHandler;
import com.epam.wl.dao.book_handlers.BookInstanceListHandler;
import com.epam.wl.dao.book_handlers.BookListHandler;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class BookDAO {
    private Executor executor;
    private final ResultHandler<List<Book>> bookListHandler;
    private final ResultHandler<List<BookInstance>> bookInstanceListHandler;
    private final ResultHandler<Integer> bookIdHandler;
    private final ResultHandler<Integer> bookInstanceIdHandler;

    public BookDAO(DataSource dataSource) {
        this.executor = new Executor(dataSource);
        this.bookListHandler = new BookListHandler();
        this.bookInstanceListHandler = new BookInstanceListHandler();
        this.bookIdHandler = new BookIdHandler();
        this.bookInstanceIdHandler = new BookInstanceIdHandler();
    }

    public List<Book> getAllBooks() throws SQLException {
        final String query = "SELECT * FROM book";
        return executor.executeQuery(query, bookListHandler);
    }

    public List<BookInstance> getAllBookInstances() throws SQLException {
        final String query = "SELECT * FROM book_instance";
        return executor.executeQuery(query, bookInstanceListHandler);
    }

    public int getBookId(String author, String title, int year) throws SQLException {
        final String query = String.format("SELECT * FROM book WHERE author='%s' AND title='%s' AND year=%d",
                author, title, year);
        return executor.executeQuery(query, bookIdHandler);
    }

    public int getFreeBookInstanceId(int bookId) throws SQLException {
        final String query = String.format("SELECT * FROM book_instance LEFT JOIN book_order " +
                "ON book_instance.id=book_order.book_instanceid WHERE " +
                "book_instance.bookid=%d AND book_instanceid IS NULL", bookId);

        return executor.executeQuery(query, bookInstanceIdHandler);
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

        final String update = String.format("INSERT INTO book_instance(bookid) VALUES (%d)", bookId);
        executor.executeUpdate(update);
    }

    private void addNewBook(String author, String title, int year) throws SQLException {
        final String update = String.format("INSERT INTO book(author, title, year) VALUES ('%s', '%s', %d)",
                author, title, year);
        executor.executeUpdate(update);
    }
}