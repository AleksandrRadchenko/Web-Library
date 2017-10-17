package com.epam.wl.dao;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.executor.Executor;
import com.epam.wl.services.BookOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BookOrderServiceTest implements TestData {
    private static final List<User> allUsers = users;
    private final BookOrderService bookOrderService = BookOrderService.getInstance();

    @BeforeEach
    void setUp() throws SQLException {
        Executor.resetTestDataSource();
    }

    @Test
    void getAllUsers() throws SQLException {
        final List<User> actual = bookOrderService.getAllUsersWithOrders();
        actual.sort(Comparator.comparing(User::getId));
        assertThat(actual, is(allUsers));
    }

    @Test
    void getBookOrderByUserId() throws SQLException {
        final int userid = 3;
        final List<BookOrder> expected = new ArrayList<>(Collections.singletonList(bo3));
        final List<BookOrder> actual = bookOrderService.getByUserId(userid);
        actual.sort(Comparator.comparing(BookOrder::getId));
        assertThat(actual, is(expected));
    }

    @Test
    void getAllBookOrders() throws SQLException {
        final List<BookOrder> expected = bookOrders;
        final List<BookOrder> actual = bookOrderService.getAll();
        actual.sort(Comparator.comparing(BookOrder::getId));
        assertThat(actual, is(expected));
    }

}
