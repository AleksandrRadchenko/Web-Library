package com.epam.wl.dao;

import com.epam.wl.DBHelper;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.BookOptions;
import com.epam.wl.enums.UserOrderStatus;
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
        bookOrderDAO = new BookOrderDAO(dataSource);
        entireTable = new ArrayList<>();
        entireTable.add(new BookOrder(1, 4, 1, BookOptions.SUBSCRIPTION));
        entireTable.add(new BookOrder(2, 6, 2, BookOptions.SUBSCRIPTION));
        entireTable.add(new BookOrder(3, 11, 3, BookOptions.READING_ROOM));
        entireTable.add(new BookOrder(4, 10, 4, BookOptions.READING_ROOM));

    }

    @Test
    void createOneRow() throws SQLException {
        BookInstance bookInstance = new BookInstance(1, 1);
        UserOrder userOrder = new UserOrder(1,1, 1, UserOrderStatus.IN_PROGRESS);
        BookOptions bookOptions = BookOptions.SUBSCRIPTION;
        int result = bookOrderDAO.create(bookInstance, userOrder, bookOptions);
        assertThat(result, is(1));
        //Check if created row is in DB for real
        assertThat(bookOrderDAO.getById(5).get(), is(new BookOrder(5, bookInstance.getId(), userOrder.getId(), bookOptions)));
    }

    @Test
    void getAllFourRows() throws SQLException {
        List<BookOrder> expected = entireTable;
        List<BookOrder> actual = bookOrderDAO.getAll();
        assertThat(actual, is(expected));
    }

    @Test
    void getById() throws SQLException {
        BookOrder expected = entireTable.get(2);
        BookOrder actual = bookOrderDAO.getById(3).get();
        assertThat(actual, is(expected));
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
        int expectedInt = 1;
        BookOrder newBookOrder = new BookOrder(3, 9, 2, BookOptions.READING_ROOM);
        int actualInt = bookOrderDAO.update(newBookOrder);
        assertThat(actualInt, is(expectedInt));
        BookOrder actual = bookOrderDAO.getById(newBookOrder.getId()).get();
        assertThat(actual, is(newBookOrder));
    }

    @Test
    void deleteById() throws SQLException {
        int expectedInt = 1;
        int idToDelete = 3;
        int actualInt = bookOrderDAO.deleteById(idToDelete);
        assertThat(actualInt, is(expectedInt));
        assertThrows(NoSuchElementException.class, () -> bookOrderDAO.getById(idToDelete).get());
    }

    @AfterEach
    void tearDown() throws SQLException {
        dataSource.shutdown();
    }
}