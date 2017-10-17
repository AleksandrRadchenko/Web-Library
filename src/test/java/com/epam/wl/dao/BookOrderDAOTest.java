package com.epam.wl.dao;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.executor.Executor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
class BookOrderDAOTest implements TestData {
    private final BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance();

    @BeforeEach
    void setUp() throws SQLException {
        Executor.resetTestDataSource();
    }

    @Test
    void createOneRow() throws SQLException {
        int initialRows = bookOrderDAO.getAll().size();
        bookOrderDAO.create(bo5.getBookInstance().getId(), bo5.getUserOrder().getId(), bo5.getBookOption());
        int finalRows = bookOrderDAO.getAll().size();
        assertThat(initialRows, is(finalRows - 1));
        //Check if created row is in DB for real
        BookOrder actual = bookOrderDAO.getById(finalRows).get();
        assertThat(actual.getBookInstance(), is(bo5.getBookInstance()));
        assertThat(actual.getUserOrder(), is(bo5.getUserOrder()));
        assertThat(actual.getBookOption(), is(bo5.getBookOption()));
    }

    @Test
    void getAllFourRows() throws SQLException {
        List<BookOrder> expected = bookOrders;
        List<BookOrder> actual = bookOrderDAO.getAll();
        assertThat(actual, is(expected));
    }

    @Test
    void getById() throws SQLException {
        int expectedBookInstance = 11;
//        BookOrder expected = entireTable.get(2);
        BookOrder actual = bookOrderDAO.getById(3).get();
        assertThat(actual.getBookInstance().getId(), is(expectedBookInstance));
    }

    @Test
    void failToGetByWrongId() throws SQLException {
        assertThrows(NoSuchElementException.class, bookOrderDAO.getById(98456456)::get);
    }

    @Test
    void failToGetByNegativeId() throws SQLException {
        assertThrows(NoSuchElementException.class, bookOrderDAO.getById(-5)::get);
    }
}