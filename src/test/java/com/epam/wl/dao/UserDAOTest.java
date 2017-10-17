package com.epam.wl.dao;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import com.epam.wl.executor.Executor;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserDAOTest implements TestData {

    private UserDAO userDAO = UserDAO.getInstance();

    @BeforeEach
    public void initDatabase() throws SQLException {
        Executor.resetTestDataSource();
    }

    @Test
    void testAddUser() throws SQLException {
        userDAO.addUser("Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER);
        assertThat(u5, is(userDAO.getUserByID(5).get()));
    }

    @Test
    void testUpdateUser() throws SQLException {
        userDAO.updateUser(2, "Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER);
        assertThat(u5.getName(), is(userDAO.getUserByID(2).get().getName()));
        assertThat(u5.getLastname(), is(userDAO.getUserByID(2).get().getLastname()));
        assertThat(u5.getEmail(), is(userDAO.getUserByID(2).get().getEmail()));
        assertThat(u5.getPasswordHash(), is(userDAO.getUserByID(2).get().getPasswordHash()));
    }

    @Test
    void testDeleteUserById() throws SQLException {
        List<User> expectedUsers = new ArrayList<>(4);
        expectedUsers.addAll(users);
        expectedUsers.remove(u3);
        userDAO.deleteUserById(3);
        assertThat(expectedUsers, is(userDAO.getAllUsers()));
    }

    @Test
    void testGetAllUsers() throws SQLException {
        assertThat(users, is(userDAO.getAllUsers()));
    }

    @Test
    void testGetUserByEmailAndPassword() throws SQLException {
        assertThat(u2, is(userDAO.getUserByEmailAndPassword("fedor@ivan.ru", "fdfsdcdrfdsfzc").get()));
    }

    @Test
    void testGetUserByNameAndLastName() throws SQLException {
        assertThat(u2, is(userDAO.getUserByNameAndLastName("Федор", "Федоров").get()));
    }

    @Test
    void testGetUserByID() throws SQLException {
        assertThat(u3, is(userDAO.getUserByID(3).get()));
    }

    @Test
    void testIsLibrarian() throws SQLException {
        final UserRole isLibrarian = UserRole.LIBRARIAN;
        assertThat(isLibrarian, is(u4.getRole()));
    }

    @Test
    void testGetUserByEmail() throws SQLException {
        final String email = "ivan@ivan.ru";
        assertThat(u1, is(userDAO.getUserByEmail(email).get()));
    }

    @Test
    void getUserByWrongId() throws SQLException {
        assertThrows(NoSuchElementException.class, userDAO.getUserByID(5776764)::get);
    }

    @Test
    void getUserByWrongEmail() throws SQLException {
        assertThrows(NoSuchElementException.class, userDAO.getUserByEmail("@@@@@")::get);
    }

    @Test
    void getUserByWrongEmailAndPassword() throws SQLException {
        assertThrows(NoSuchElementException.class, userDAO.getUserByEmailAndPassword("@@@", "gggg")::get);
        assertThrows(NoSuchElementException.class, userDAO.getUserByEmailAndPassword("ivan@ivan.ru", "gggg")::get);
        assertThrows(NoSuchElementException.class, userDAO.getUserByEmailAndPassword("@@@", "fdfsdcdzc")::get);
    }

    @Test
    void getUserByWrongNameAndLastName() throws SQLException {
        assertThrows(NoSuchElementException.class, userDAO.getUserByNameAndLastName("Vova", "Sidoroff")::get);
    }
}