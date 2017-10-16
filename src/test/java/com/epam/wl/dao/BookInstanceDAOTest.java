package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;

public class BookInstanceDAOTest {
    private final BookInstanceDAO bookInstanceDAO = BookInstanceDAO.getInstance();

    @Test
    void getById() throws SQLException {
        BookInstance actual = bookInstanceDAO.getById(10).get(); // 4
        BookInstance expextedBookId = new BookInstance(10, new Book(4, "Лев Толстой", "Война и мир", 1978));
        assertThat(actual, Is.is(expextedBookId));
    }

//    @Test
//    void getAll() throws SQLException {
//        BookInstance actual = bookInstanceDAO.getById(10).get(); // 4
//        BookInstance expextedBookId = new BookInstance(10, new Book(4, "Лев Толстой", "Война и мир", 1978));
//        assertThat(actual, Is.is(expextedBookId));
//    }
}
