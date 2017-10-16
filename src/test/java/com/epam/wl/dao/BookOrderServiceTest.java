package com.epam.wl.dao;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.services.BookOrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.SQLException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BookOrderServiceTest implements TestData {
    private EmbeddedDatabase dataSource;
    private BookOrderService bookOrderService;
    private static final List<User> allUsers = users;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = DBHelper.getNewEmbeddedDatabase();
        bookOrderService = BookOrderService.getInstance();
    }

    @AfterEach
    void tearDown() throws SQLException {
        dataSource.shutdown();
    }

    @Test
    void getAllUsers() {
        final List<User> actual = bookOrderService.getAllUsersWithOrders();
        actual.sort(Comparator.comparing(User::getId));
        assertThat(actual, is(allUsers));
    }

    @Test
    void getBookOrderByUserId() {
        final int userid = 3;
        final List<BookOrder> expected = new ArrayList<>(Collections.singletonList(bo3));
        final List<BookOrder> actual = bookOrderService.getByUserId(userid);
        actual.sort(Comparator.comparing(BookOrder::getId));
        assertThat(actual, is(expected));
    }

    @Test
    void getAllBookOrders() {
        final List<BookOrder> expected = bookOrders;
        final List<BookOrder> actual = bookOrderService.getAll();
        actual.sort(Comparator.comparing(BookOrder::getId));
        assertThat(actual, is(expected));
    }

}
