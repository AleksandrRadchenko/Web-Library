package com.epam.wl.dao;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class OrderDAOTest {

    private OrderDAO orderDAO;
    private EmbeddedDatabase dataSource;

    @BeforeEach
    public void init() {
        dataSource = getEmbeddedDatabase();
        orderDAO = new OrderDAO(dataSource);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        dataSource.shutdown();
    }

    @Test
    void testSetOrderStatus() {
        UserOrder expectedOrder = new UserOrder(2, 2, 2, OrderStatus.IN_PROGRESS);
        UserOrder actualOrder = null;
        orderDAO.setOrderStatus(2, OrderStatus.IN_PROGRESS);

        try (Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM user_order WHERE id = 2");
            result.next();
            int id = result.getInt("id");
            int userID = result.getInt("userid");
            int editionid = result.getInt("bookid");
            OrderStatus status = OrderStatus.valueOf(result.getString("status"));
            actualOrder = new UserOrder(id, editionid, userID, status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(expectedOrder, is(actualOrder));
    }

    @Test
    void testGetAll() {
        List<UserOrder> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new UserOrder(1, 1, 1, OrderStatus.IN_PROGRESS));
        expectedOrderList.add(new UserOrder(2, 2, 2, OrderStatus.NEW));
        expectedOrderList.add(new UserOrder(3, 3, 3, OrderStatus.NEW));
        expectedOrderList.add(new UserOrder(4, 4, 4, OrderStatus.NEW));

        assertThat(expectedOrderList, is(orderDAO.getAll()));
    }

    private EmbeddedDatabase getEmbeddedDatabase() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        final EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();
        return db;
    }
}
