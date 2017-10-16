package com.epam.wl.dao;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
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

class UserOrderDAOTest implements TestData {

    private UserOrderDAO userOrderDAO;
    private EmbeddedDatabase dataSource;

    @BeforeEach
    public void initDatabase() {
        dataSource = DBHelper.getEmbeddedDatabase();
        userOrderDAO = new UserOrderDAO(dataSource);
    }

    @AfterEach
    public void dropDatabase() throws SQLException {
        dataSource.shutdown();
    }

    @Test
    void testCreateUserOrder() throws SQLException {
        userOrderDAO.createNewUserOrder(4, 1);
        UserOrder expectedUserOrder = new UserOrder(
                6, 1, "Иван", "Иванов", "ivan@ivan.ru",
                "Война и мир", "Лев Толстой", 1978, UserOrderStatus.NEW);
        assertThat(expectedUserOrder, is(userOrderDAO.getUserOrderByID(6).get()));
    }

    @Test
    void testSetOrderStatus() throws SQLException {
        UserOrder expectedUserOrder = new UserOrder(
                2, 2, "Федор", "Федоров", "fedor@ivan.ru",
                "Java: A Beginner's Guide, Sixth Edition", "Herbert Schildt",
                2014, UserOrderStatus.CLOSED);
        userOrderDAO.setUserOrderStatus(2, UserOrderStatus.CLOSED);
        UserOrder actualUserOrder = userOrderDAO.getUserOrderByID(2).get();

        assertThat(expectedUserOrder, is(actualUserOrder));
    }

    @Test
    void testGetAllUserOrders() throws SQLException {
        List<UserOrder> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new UserOrder(
                1, 1, "Иван", "Иванов", "ivan@ivan.ru", "Азбука",
                "Петр Иванов", 1954, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                2, 2, "Федор", "Федоров", "fedor@ivan.ru", "Java: A Beginner's Guide, Sixth Edition",
                "Herbert Schildt", 2014, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                3, 3, "Петр", "Петров", "petr@ivan.ru", "Thinking in Java (4th Edition)",
                "Bruce Eckel", 2016, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                4, 4, "Семен", "Семенов", "semen@ivan.ru", "Война и мир",
                "Лев Толстой", 1978, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                5, 1, "Иван", "Иванов", "ivan@ivan.ru", "Thinking in Java (4th Edition)",
                "Bruce Eckel", 2016, UserOrderStatus.NEW));

        assertThat(expectedOrderList, is(userOrderDAO.getAllUserOrders()));
    }

    @Test
    void testGetUserOrderByID() throws SQLException {
        UserOrder expectedUserOrder = new UserOrder(
                2, 2, "Федор", "Федоров", "fedor@ivan.ru", "Java: A Beginner's Guide, Sixth Edition",
                "Herbert Schildt", 2014, UserOrderStatus.IN_PROGRESS);
        UserOrder actualUserOrder = userOrderDAO.getUserOrderByID(2).get();

        assertThat(expectedUserOrder, is(actualUserOrder));
    }

    @Test
    void testGetUserByStatus() throws SQLException {
        List<UserOrder> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new UserOrder(
                1, 1, "Иван", "Иванов", "ivan@ivan.ru", "Азбука",
                "Петр Иванов", 1954, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                2, 2, "Федор", "Федоров", "fedor@ivan.ru", "Java: A Beginner's Guide, Sixth Edition",
                "Herbert Schildt", 2014, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                3, 3, "Петр", "Петров", "petr@ivan.ru", "Thinking in Java (4th Edition)",
                "Bruce Eckel", 2016, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                4, 4, "Семен", "Семенов", "semen@ivan.ru", "Война и мир",
                "Лев Толстой", 1978, UserOrderStatus.IN_PROGRESS));

        assertThat(expectedOrderList, is(userOrderDAO.getUserOrderByStatus(UserOrderStatus.IN_PROGRESS)));
    }

    @Test
    void testGetUserOrderByUserId() throws SQLException {
        List<UserOrder> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new UserOrder(
                1, 1, "Иван", "Иванов", "ivan@ivan.ru", "Азбука",
                "Петр Иванов", 1954, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(
                5, 1, "Иван", "Иванов", "ivan@ivan.ru", "Thinking in Java (4th Edition)",
                "Bruce Eckel", 2016, UserOrderStatus.NEW));

        assertThat(expectedOrderList, is(userOrderDAO.getUserOrderByUserId(1)));
    }

    @Test
    void failToGetByWrongId() throws SQLException {
        assertThrows(NoSuchElementException.class, () -> userOrderDAO.getUserOrderByID(5345345).get());
    }

    @Test
    void failToGetByNegativeId() throws SQLException {
        assertThrows(NoSuchElementException.class, () -> userOrderDAO.getUserOrderByID(-1).get());
    }
}
