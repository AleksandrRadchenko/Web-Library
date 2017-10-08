package com.epam.wl.dao;

import com.epam.wl.DBHelper;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.BookOptions;
import com.epam.wl.enums.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookOrderDAOTest {
    private EmbeddedDatabase dataSource;
    private BookOrderDAO bookOrderDAO;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = DBHelper.getEmbeddedDatabase();
        bookOrderDAO = new BookOrderDAO(dataSource);
    }

    @Test
    void createOneRow() throws SQLException {
        BookInstance bookInstance = new BookInstance(1, 1);
        UserOrder userOrder = new UserOrder(1,1, 1, OrderStatus.IN_PROGRESS);
        BookOptions bookOptions = BookOptions.SUBSCRIPTION;
        int result = bookOrderDAO.create(bookInstance, userOrder, bookOptions);
        assertThat(result, is(1));
        //Check if created row is in DB for real
        Statement st = dataSource.getConnection().createStatement();
        st.executeQuery("SELECT book_instanceid, user_orderid, option FROM book_order WHERE id=5");
        ResultSet resultSet = st.getResultSet();
        resultSet.next();
        assertThat(resultSet.getInt("book_instanceid"), is(bookInstance.getId()));
        assertThat(resultSet.getInt("user_orderid"), is(userOrder.getId()));
        assertThat(resultSet.getString("option"), is(bookOptions.name()));
    }

    @Test
    void getAllFourRows() throws SQLException {
        List<BookOrder> expected = new ArrayList<>();
        expected.add(new BookOrder(1, 4, 1, BookOptions.SUBSCRIPTION));
        expected.add(new BookOrder(2, 6, 2, BookOptions.SUBSCRIPTION));
        expected.add(new BookOrder(3, 11, 3, BookOptions.READING_ROOM));
        expected.add(new BookOrder(4, 10, 4, BookOptions.READING_ROOM));
        List<BookOrder> actual = bookOrderDAO.getAll();
        assertThat(actual, is(expected));
    }

    @Test
    void getById() throws SQLException {
        BookOrder expected = new BookOrder(3,11,3,BookOptions.READING_ROOM);
        BookOrder actual = bookOrderDAO.getById(3).get();
        assertThat(actual, is(expected));
    }

    @Test
    void failToGetByWrongId() throws SQLException {
        assertThrows(NoSuchElementException.class, () -> bookOrderDAO.getById(98456456).get());
    }

    @Test
    void failToGetByNegativeId() throws SQLException {
        assertThrows(NoSuchElementException.class, () -> bookOrderDAO.getById(-5).get());
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

//    @Test
//    void deleteById() throws SQLException {
//        int expectedInt = 1;
//        int bookOrderToDeleteId = 3;
//        int actualInt = bookOrderDAO.delete(bookOrderToDeleteId);
//        assertThat(actualInt, is(expectedInt));
//        BookOrder actual = bookOrderDAO.getById(bookOrderToDeleteId);
//        assertThat(actual, is(newBookOrder));
//    }
//
    @AfterEach
    void tearDown() throws SQLException {
        dataSource.shutdown();
    }
}