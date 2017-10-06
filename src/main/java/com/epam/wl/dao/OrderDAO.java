package com.epam.wl.dao;

import com.epam.wl.entities.Order;
import com.epam.wl.enums.OrderStatus;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private DataSource dataSource;

    public OrderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createNew(final int userID, final int bookID) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user_order (userid, bookid) VALUES(?, ?);");
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, bookID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setOrderStatus(final int orderID, final OrderStatus status) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE user_order SET status = ? WHERE id = ?");
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAll() {
        List<Order> ordersList = new ArrayList();
        try (Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet result = statement.executeQuery(
                    "SELECT * FROM user_order");
            while (result.next()) {
                int id = result.getInt("id");
                int editionID = result.getInt("bookid");
                int userID = result.getInt("userid");
                OrderStatus status = OrderStatus.valueOf(result.getString("status"));
                ordersList.add(new Order(id, editionID, userID, status));
            }
            return ordersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
