package com.epam.wl.dao;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.Executor;
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

    private UserOrderDAO userOrderDAO = UserOrderDAO.getInstance();

    @BeforeEach
    public void initDatabase() {
            Executor.resetTestDataSource();
    }

    @Test
    void testCreateUserOrder() throws SQLException {
        userOrderDAO.createNewUserOrder(1, 3);
        UserOrder expectedUserOrder = uo5;
        assertThat(expectedUserOrder, is(userOrderDAO.getUserOrderByID(u5.getId()).get()));
    }

    @Test
    void testSetOrderStatus() throws SQLException {
        UserOrder expectedUserOrder = new UserOrder(2, u2, b2, UserOrderStatus.CLOSED);;
        userOrderDAO.setUserOrderStatus(2, UserOrderStatus.CLOSED);
        UserOrder actualUserOrder = userOrderDAO.getUserOrderByID(2).get();

        assertThat(expectedUserOrder, is(actualUserOrder));
    }

    @Test
    void testGetAllUserOrders() throws SQLException {
        List<UserOrder> expectedOrderList = userOrders;
        assertThat(expectedOrderList, is(userOrderDAO.getAllUserOrders()));
    }

    @Test
    void testGetUserOrderByID() throws SQLException {
        UserOrder expectedUserOrder = uo2;
        UserOrder actualUserOrder = userOrderDAO.getUserOrderByID(uo2.getId()).get();

        assertThat(expectedUserOrder, is(actualUserOrder));
    }

    @Test
    void testGetUserByStatus() throws SQLException {
        List<UserOrder> expectedOrderList = userOrders;

        assertThat(expectedOrderList, is(userOrderDAO.getUserOrderByStatus(UserOrderStatus.IN_PROGRESS)));
    }

    @Test
    void testGetUserOrderByUserId() throws SQLException {
        List<UserOrder> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(uo3);

        assertThat(expectedOrderList, is(userOrderDAO.getUserOrderByUserId(uo3.getId())));
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
