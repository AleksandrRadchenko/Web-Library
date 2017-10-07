package com.epam.wl.dao;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDAOTest {

    private UserDAO userDAO;
    private EmbeddedDatabase dataSource;

    @Before
        //todo fails every test if BeforeEach, why?
    void init() {
        dataSource = getDataSource();
        userDAO = new UserDAO(dataSource);//so it will be a new database each time
    }

    private EmbeddedDatabase getDataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        final EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();
        return db;
    }

    @After
    void tearDown() throws SQLException {
        dataSource.shutdown();
    }

    @Test
    void testIsAddUserSucceed() {
        UserDAO userDAO = new UserDAO(getDataSource());//fails test if you run whole test filem OK if only that test(??)
        assertEquals(true, userDAO.isAddUserSucceed(
                "Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER));
    }

    @Test
    void testIsUpdateUserSucceed() {
        UserDAO userDAO = new UserDAO(getDataSource());
        assertEquals(true, userDAO.isUpdateUserSucceed(1, "АНДРЕА", "Иванов",
                "ivan@ivan.ru", "fdfsdcdzc", UserRole.USER));//id [1-3]
    }

    @Test
    void testIsDeleteUserByIdSucceed() {
        UserDAO userDAO = new UserDAO(getDataSource());
        assertEquals(true, userDAO.isDeleteUserByIdSucceed(1));//id [1-3] - EXISTING ID
    }

    @Test
    void testGetAllUsers() {
        UserDAO userDAO = new UserDAO(getDataSource());//fails test if you run whole test filem OK if only that test(??)
        User userEntityFirst = new User(1, "Иван", "Иванов", "ivan@ivan.ru", "fdfsdcdzc", UserRole.USER);
        User userEntitySecond = new User(2, "Федор", "Федоров", "fedor@ivan.ru", "fdfsdcdrfdsfzc", UserRole.USER);
        User userEntityThird = new User(3, "Петр", "Петров", "petr@ivan.ru", "fdfsdcdssdfdzc", UserRole.USER);
        User userEntityFourth = new User(4, "Семен", "Семенов", "semen@ivan.ru", "fdfsdcdssdfdzc", UserRole.USER);
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userEntityFirst);
        expectedUsers.add(userEntitySecond);
        expectedUsers.add(userEntityThird);
        expectedUsers.add(userEntityFourth);
        assertEquals(expectedUsers, userDAO.getAllUsers());
        //todo assure that comparing hashcodes is impossible and should overload equals and hashCode
    }

    @Test
    void testGetUserByLogin() {
        UserDAO userDAO = new UserDAO(getDataSource());
        User userEntity = new User(2, "Федор", "Федоров", "fedor@ivan.ru", "fdfsdcdrfdsfzc", UserRole.USER);
        assertEquals(userEntity.getId(), userDAO.getUserByLogin("fedor@ivan.ru", "fdfsdcdrfdsfzc").getId());
    }

}