package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.Order;
import com.epam.wl.entities.User;
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

    public void create(User user, Book book) {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO order (userid, editionid) VALUES(?, ?);");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void setInProgress(int orderID) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE order SET status = 'IN_PROGRESS' WHERE id = ?");
            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void close(int orderID) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE order SET status = 'CLOSED' WHERE id = ?");
            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Order> getAll() {
        List<Order> ordersList = new ArrayList();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM user_order");
            while (result.next()) {
                int id = result.getInt("id");
                int editionID = result.getInt("editionid");
                int userID = result.getInt("userid");
                OrderStatus status = OrderStatus.NEW;
                switch (result.getString("status")) {
                    case "NEW":
                        status = OrderStatus.NEW;
                        break;
                    case "IN_PROGRESS":
                        status = OrderStatus.IN_PROGRESS;
                        break;
                    case "CLOSED":
                        status = OrderStatus.CLOSED;
                        break;
                }
                ordersList.add(new Order(id, editionID, userID, status));
            }
            return ordersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getNew() {
        return null;
    }
}
