package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.Order;
import com.epam.wl.entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreOrderDAO implements OrderDAO {
    private DataSource dataSource;

    public PostgreOrderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
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

    @Override
    public void setInProgress(int orderID) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE order SET status = 'IN_PROGRESS' WHERE id = ?");
            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void close(int orderID) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE order SET status = 'CLOSED' WHERE id = ?");
            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> ordersList = new ArrayList();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM order");
            while (result.next()) {
                int id = result.getInt("id");
//                int editionID
//
//
//                ordersList.add(new Order());

                //TODO Понять как создавать заказ и нужно ли его создавать. Нужно ли выводить из таблицы список всех заказов
                //TODO Список новых заказов и тд.

                System.out.println("ID: " + result.getInt("id")
                        + " Name: " + result.getString("name")
                        + "eMail: " + result.getString("email"));
                return ordersList;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<Order> getNew() {
        return null;
    }
}
