package com.epam.wl.dao;

import com.epam.wl.entities.Order;
import com.epam.wl.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    private static OrderDAO orderDAO;

    @BeforeEach
    public void init() {
        orderDAO = new OrderDAO(getDataSource());
    }

    @Test
    void testGetAll(){
        List<Order> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new Order(1,1,1, OrderStatus.IN_PROGRESS));
        expectedOrderList.add(new Order(2,2,2, OrderStatus.NEW));
        expectedOrderList.add(new Order(3,3,3, OrderStatus.NEW));
        expectedOrderList.add(new Order(4,4,4, OrderStatus.NEW));

        assertIterableEquals(expectedOrderList, orderDAO.getAll());
    }

    private DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();
        return db;
    }
}
