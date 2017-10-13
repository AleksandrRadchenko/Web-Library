package com.epam.wl.dao;

import com.epam.wl.DBHelper;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.enums.BookOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
class BookOrderDAOTest {
    private EmbeddedDatabase dataSource;
    private BookOrderDAO bookOrderDAO;
    private List<BookOrder> entireTable;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = DBHelper.getEmbeddedDatabase();
        bookOrderDAO = BookOrderDAO.getInstance(dataSource);
        entireTable = new ArrayList<>();
        entireTable.add(new BookOrder(1, 4, 1, BookOption.SUBSCRIPTION));
        entireTable.add(new BookOrder(2, 6, 2, BookOption.SUBSCRIPTION));
        entireTable.add(new BookOrder(3, 11, 3, BookOption.READING_ROOM));
        entireTable.add(new BookOrder(4, 10, 4, BookOption.READING_ROOM));

    }

    @Test
    void createOneRow() throws SQLException {
        int bookInstanceId = 1;
        int userOrderId = 1;
        BookOption bookOption = BookOption.SUBSCRIPTION;
        bookOrderDAO.create(bookInstanceId, userOrderId, bookOption);
        BookOrder expected = new BookOrder(5, bookInstanceId, userOrderId, bookOption);
        //Check if created row is in DB for real
        assertRequiredFields(bookOrderDAO.getById(5).get(), expected);
//        assertThat(bookOrderDAO.getById(5).get().getId(), is(expected.getId()));
    }

    @Test
    void getAllFourRows() throws SQLException {
        List<BookOrder> expected = entireTable;
        List<BookOrder> actual = bookOrderDAO.getAll();
        for (int i = 0; i < actual.size(); i++) {
            assertRequiredFields(actual.get(i), expected.get(i));
        }
    }

    @Test
    void getById() throws SQLException {
        BookOrder expected = entireTable.get(2);
        BookOrder actual = bookOrderDAO.getById(3).get();
        assertRequiredFields(actual, expected);
    }

    @Test
    void getByUserId() throws SQLException {
        Object expected = entireTable.get(2);
        Object actual = bookOrderDAO.getByUserId(1); // TODO: 12.10.2017 Optional here
//        assertThat(actual, is(expected));
    }

    @Test
    void failToGetByWrongId() throws SQLException {
        assertThrows(NoSuchElementException.class, bookOrderDAO.getById(98456456)::get);
    }

    @Test
    void failToGetByNegativeId() throws SQLException {
        assertThrows(NoSuchElementException.class, bookOrderDAO.getById(-5)::get);
    }

    @Test
    void update() throws SQLException {
        BookOrder newBookOrder = new BookOrder(2, 9, 2, BookOption.READING_ROOM);
        BookOrder anotherExpected = bookOrderDAO.getById(1).get();
        bookOrderDAO.update(newBookOrder);
        BookOrder actual = bookOrderDAO.getById(newBookOrder.getId()).get();
        BookOrder anotherActual = bookOrderDAO.getById(1).get();
        assertRequiredFields(actual, newBookOrder);
        assertThat(anotherActual, is(anotherExpected));
    }

    @Test
    void deleteById() throws SQLException {
        int idToDelete = 3;
        bookOrderDAO.deleteById(idToDelete);
        assertThrows(NoSuchElementException.class, () -> bookOrderDAO.getById(idToDelete).get());
    }

    @AfterEach
    void tearDown() throws SQLException {
        dataSource.shutdown();
    }

    private void assertRequiredFields(BookOrder actual, BookOrder expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getBookInstanceId(), is(expected.getBookInstanceId()));
        assertThat(actual.getUserOrderId(), is(expected.getUserOrderId()));
        assertThat(actual.getBookOption(), is(expected.getBookOption()));
    }
}