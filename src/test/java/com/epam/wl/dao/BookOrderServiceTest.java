package com.epam.wl.dao;

import com.epam.wl.DBHelper;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import com.epam.wl.services.BookOrderService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class BookOrderServiceTest {
    private EmbeddedDatabase dataSource;
    private BookOrderService bookOrderService;
    private static List<User> allUsers;

    @BeforeAll
    static void init() {
        allUsers = new ArrayList<>();
        allUsers.add(new User(1, "Иван", "Иванов", "ivan@ivan.ru", "", UserRole.USER));
        allUsers.add(new User(2, "Федор", "Федоров", "fedor@ivan.ru", "", UserRole.USER));
        allUsers.add(new User(3, "Петр", "Петров", "petr@ivan.ru", "", UserRole.USER));
        allUsers.add(new User(4, "Семен", "Семенов", "semen@ivan.ru", "", UserRole.USER));
    }

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
        List<User> actual = bookOrderService.getAllUsers();
        actual.sort(Comparator.comparing(User::getId));
        assertThat(actual, Is.is(allUsers));
    }


}
