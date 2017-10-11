package com.epam.wl.dao;

import com.epam.wl.DBHelper;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BookDAOTest {
    private EmbeddedDatabase dataSource;
    private BookDAO bookDAO;

    /**
     * Initializes database before each call test method
     */
    @BeforeEach
    void initDatabase() {
        dataSource = DBHelper.getEmbeddedDatabase();
        bookDAO = new BookDAO(dataSource);
    }

    /**
     * Shuts down database after each call test method
     */
    @AfterEach
    void dropDatabase() {
        dataSource.shutdown();
    }

    @Test
    void testGetAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Петр Иванов", "Азбука", 1954));
        books.add(new Book(2, "Herbert Schildt", "Java: A Beginner's Guide, Sixth Edition", 2014));
        books.add(new Book(3, "Bruce Eckel", "Thinking in Java (4th Edition)", 2016));
        books.add(new Book(4, "Лев Толстой", "Война и мир", 1978));

        assertThat(books, is(bookDAO.getAllBooks()));
    }

    @Test
    void testGetAllBookInstances() throws SQLException {
        List<BookInstance> bookInstances = new ArrayList<>();
        bookInstances.add(new BookInstance(1, 1));
        bookInstances.add(new BookInstance(2, 1));
        bookInstances.add(new BookInstance(3, 1));
        bookInstances.add(new BookInstance(4, 1));
        bookInstances.add(new BookInstance(5, 2));
        bookInstances.add(new BookInstance(6, 2));
        bookInstances.add(new BookInstance(7, 2));
        bookInstances.add(new BookInstance(8, 2));
        bookInstances.add(new BookInstance(9, 2));
        bookInstances.add(new BookInstance(10, 4));
        bookInstances.add(new BookInstance(11, 3));

        assertThat(bookInstances, is(bookDAO.getAllBookInstances()));
    }

    @Test
    void testGetBookId() throws SQLException {
        int id = 2;
        String author = "Herbert Schildt";
        String title = "Java: A Beginner's Guide, Sixth Edition";
        int year = 2014;

        assertThat(id, is(bookDAO.getBookId(author, title, year)));
    }

    @Test
    void testGetFreeBookInstanceId() throws SQLException {
        int bookId = 2;
        int expectedBookInstanceId = 5;

        assertThat(expectedBookInstanceId, is(bookDAO.getFreeBookInstanceId(bookId)));
    }

    @Test
    void testAddNewBookInstance() throws SQLException {
        List<BookInstance> expectedBookInstances = bookDAO.getAllBookInstances();
        expectedBookInstances.add(new BookInstance(expectedBookInstances.size() + 1, 5));

        bookDAO.addNewBookInstance("Jerome David Salinger","The Catcher in the Rye", 2012);

        assertThat(expectedBookInstances, is(bookDAO.getAllBookInstances()));
    }
}
