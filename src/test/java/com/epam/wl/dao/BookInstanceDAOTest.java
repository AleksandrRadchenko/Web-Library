package com.epam.wl.dao;

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
        int expextedBookId = 4;
        assertThat(actual, Is.is(expextedBookId));
    }
}
