package com.epam.wl.dao;

import com.epam.wl.DBHelper;
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

class UserOrderDAOTest {

    private UserOrderDAO userOrderDAO;
    private EmbeddedDatabase dataSource;

    @BeforeEach
    public void initDatabase() {
        dataSource = DBHelper.getNewEmbeddedDatabase();
        userOrderDAO = new UserOrderDAO(dataSource);
    }

    @AfterEach
    public void dropDatabase() throws SQLException {
        dataSource.shutdown();
    }

    @Test
    void testCreateUserOrder() throws SQLException {
        userOrderDAO.createNewUserOrder(4, 1);
        UserOrder expectedUserOrder = new UserOrder(6, 4, 1, UserOrderStatus.NEW);

        assertThat(expectedUserOrder, is(userOrderDAO.getUserOrderByID(6).get()));
    }

    @Test
    void testSetOrderStatus() throws SQLException {
        UserOrder expectedUserOrder = new UserOrder(2, 2, 2, UserOrderStatus.CLOSED);
        userOrderDAO.setUserOrderStatus(2, UserOrderStatus.CLOSED);
        UserOrder actualUserOrder = userOrderDAO.getUserOrderByID(2).get();

        assertThat(expectedUserOrder, is(actualUserOrder));
    }

    @Test
    void testGetAllUserOrders() throws SQLException {
        List<UserOrder> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new UserOrder(1, 1, 1, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(2, 2, 2, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(3, 3, 3, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(4, 4, 4, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(5, 3, 1, UserOrderStatus.NEW));

        assertThat(expectedOrderList, is(userOrderDAO.getAllUserOrders()));
    }

    @Test
    void testGetUserOrderByID() throws SQLException {
        UserOrder expectedUserOrder = new UserOrder(2, 2, 2, UserOrderStatus.IN_PROGRESS);
        UserOrder actualUserOrder = userOrderDAO.getUserOrderByID(2).get();

        assertThat(expectedUserOrder, is(actualUserOrder));
    }

    @Test
    void testGetUserByStatus() throws SQLException {
        List<UserOrder> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new UserOrder(1, 1, 1, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(2, 2, 2, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(3, 3, 3, UserOrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(4, 4, 4, UserOrderStatus.IN_PROGRESS));

        assertThat(expectedOrderList, is(userOrderDAO.getUserOrderByStatus(UserOrderStatus.IN_PROGRESS)));
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
