package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookInstanceDAOTest implements TestData {
    private final BookInstanceDAO bookInstanceDAO = BookInstanceDAO.getInstance();

    @Test
    void getById() throws SQLException {
        assertThat(bi10, Is.is(bookInstanceDAO.getById(10).get()));
    }

    @Test
    void getByWrongId() throws SQLException {
        assertThrows(NoSuchElementException.class, bookInstanceDAO.getById(98456456)::get);
    }
}
