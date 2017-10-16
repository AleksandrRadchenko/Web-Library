package com.epam.wl.dao;

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
    private List<BookOrder> entireTable;
    private final BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance();

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = DBHelper.getEmbeddedDatabase();
        entireTable = new ArrayList<>();
//        entireTable.add(new BookOrder(1, 4, 1, BookOption.SUBSCRIPTION));
//        entireTable.add(new BookOrder(2, 6, 2, BookOption.SUBSCRIPTION));
//        entireTable.add(new BookOrder(3, 11, 3, BookOption.READING_ROOM));
//        entireTable.add(new BookOrder(4, 10, 4, BookOption.READING_ROOM));

    }

    @Test
    void createOneRow() throws SQLException {
        int bookInstanceId = 1;
        int userOrderId = 1;
        BookOption bookOption = BookOption.SUBSCRIPTION;
        bookOrderDAO.create(bookInstanceId, userOrderId, bookOption);
        //Check if created row is in DB for real
        assertThat(bookOrderDAO.getById(5).get().getUserOrder(), is(userOrderId));
    }

//    @Test
//    void getAllFourRows() throws SQLException {
//        List<BookOrder> expected = entireTable;
//        List<BookOrder> actual = bookOrderDAO.getAll();
//        assertThat(actual, is(expected));
//    }

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

//    @Test
//    void update() throws SQLException {
//        int expectedInt = 1;
//        BookOrder newBookOrder = new BookOrder(3, 9, 2, BookOption.READING_ROOM);
//        bookOrderDAO.update(newBookOrder);
//        BookOrder actual = bookOrderDAO.getById(newBookOrder.getId()).get();
//        assertThat(actual, is(newBookOrder));
//    }
//
    @AfterEach
    void tearDown() throws SQLException {
        dataSource.shutdown();
    }
}