package com.epam.wl.dao;

import com.epam.wl.DBHelper;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class UserDAOTest {

    private UserDAO userDAO;
    private EmbeddedDatabase dataSource;

    @BeforeEach
    public void initDatabase() {
        dataSource = DBHelper.getEmbeddedDatabase();
        userDAO = new UserDAO(dataSource);
    }

    @AfterEach
    public void dropDatabase() throws SQLException {
        dataSource.shutdown();
    }

    @Test
    void testAddUser() throws SQLException {
        userDAO.addUser("Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER);
        User expectedUser = new User(11, "Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER);
        assertThat(expectedUser, is(userDAO.getUserByID(11).get()));
    }

    @Test
    void testIsUpdateUserSucceed() throws SQLException {
        userDAO.updateUser(2, "Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER);
        User expectedUser = new User(2, "Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER);
        assertThat(expectedUser, is(userDAO.getUserByID(2).get()));
    }

    @Test
    void testDeleteUserById() throws SQLException {
        userDAO.deleteUserById(1);
        User expectedUser = new User(1, "Иван", "Иванов", "ivan@ivan.ru", "fdfsdcdzc", UserRole.USER);
        assertThat(expectedUser, is(userDAO.getUserByID(1).get()));
    }

    @Test
    void testGetAllUsers() throws SQLException {
        User userEntityFirst = new User(1, "Иван", "Иванов", "ivan@ivan.ru", "fdfsdcdzc", UserRole.USER);
        User userEntitySecond = new User(2, "Федор", "Федоров", "fedor@ivan.ru", "fdfsdcdrfdsfzc", UserRole.USER);
        User userEntityThird = new User(3, "Петр", "Петров", "petr@ivan.ru", "fdfsdcdssdfdzc", UserRole.USER);
        User userEntityFourth = new User(4, "Семен", "Семенов", "semen@ivan.ru", "fdfsdcdssdfdzc", UserRole.USER);
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userEntityFirst);
        expectedUsers.add(userEntitySecond);
        expectedUsers.add(userEntityThird);
        expectedUsers.add(userEntityFourth);
        assertThat(expectedUsers, is(userDAO.getAllUsers()));
    }

    @Test
    void testGetUserByEmailAndPassword() throws SQLException {
        User userEntity = new User(2, "Федор", "Федоров", "fedor@ivan.ru", "fdfsdcdrfdsfzc", UserRole.USER);
        assertThat(userEntity, is(userDAO.getUserByEmailAndPassword("fedor@ivan.ru", "fdfsdcdrfdsfzc").get()));
    }

    @Test
    void testGetUserByNameAndLastName() throws SQLException {
        User userEntity = new User(2, "Федор", "Федоров", "fedor@ivan.ru", "fdfsdcdrfdsfzc", UserRole.USER);
        assertThat(userEntity, is(userDAO.getUserByNameAndLastName("Федор", "Федоров").get()));
    }

    @Test
    void testGetUserByID() throws SQLException {
        User userEntity = new User(2, "Федор", "Федоров", "fedor@ivan.ru", "fdfsdcdrfdsfzc", UserRole.USER);
        assertThat(userEntity, is(userDAO.getUserByID(2).get()));
    }

}