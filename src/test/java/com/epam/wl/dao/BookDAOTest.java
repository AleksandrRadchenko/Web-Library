package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.executor.Executor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BookDAOTest implements TestData {
    private EmbeddedDatabase dataSource;
    private final BookDAO bookDAO = BookDAO.getInstance();

    /**
     * Initializes database before each call test method
     */
    @BeforeEach
    void initDatabase() {
        Executor.resetTestDataSource();
    }

    @Test
    void testGetAllBooks() throws SQLException {
        List<Book> expected = books;
        List<Book> actual = bookDAO.getAllBooks();
        assertThat(actual, is(books));
    }

    @Test
    void testGetFreeBookInstanceId() throws SQLException {
        int bookId = 2;
        int expectedBookInstanceId = 5;

        assertThat(expectedBookInstanceId, is(bookDAO.getFreeBookInstanceId(bookId)));
    }

    //    @Test
//    void testAddNewBookInstance() throws SQLException {
//        List<BookInstance> expectedBookInstances = bookDAO.getAllBookInstances();
//        expectedBookInstances.add(new BookInstance(expectedBookInstances.size() + 1, b5));
//
//        bookDAO.addNewBookInstance("Jerome David Salinger","The Catcher in the Rye", 2012);
//
//        assertThat(expectedBookInstances, is(bookDAO.getAllBookInstances()));
//    }
//
    @Test
    void testGetFreeBookInstancesForThisBook() throws SQLException {
        int bookId = 2;
        List<Integer> expectedBookInstancesIdList = new ArrayList<>();
        expectedBookInstancesIdList.add(5);
        expectedBookInstancesIdList.add(7);
        expectedBookInstancesIdList.add(8);
        expectedBookInstancesIdList.add(9);

        assertThat(expectedBookInstancesIdList, is(bookDAO.getFreeBookInstancesForThisBook(bookId)));
    }
}
