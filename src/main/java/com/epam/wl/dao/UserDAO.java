package com.epam.wl.dao;

import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAO {

    private DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public boolean isAddUserSucceed(String name, String lastname, String email, String passwordHash, UserRole userRole) {
        boolean isSucceed = false;//'Иван', 'Иванов', 'ivan@ivan.ru', 'fdfsdcdzc', 'USER'

        try (Connection connection = dataSource.getConnection()) {
            String sqlScript = "INSERT INTO user(name, lastname, email, passwordhash, role) VALUES(?, ?, ?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, passwordHash);
            preparedStatement.setString(5, userRole.toString());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                isSucceed = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSucceed;
    }

    public boolean isUpdateUserSucceed(int id, String name, String lastname, String email, String passwordHash, UserRole userRole) {
        boolean isSucceed = false;
        try (Connection connection = dataSource.getConnection()) {
            String sqlScript = "UPDATE user SET name=?, lastname=?, email=?, passwordhash=? WHERE id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, passwordHash);
            preparedStatement.setInt(5, id);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                isSucceed = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSucceed;
    }

    public boolean isDeleteUserByIdSucceed(int id) {
        boolean isSucceed = false;
        try (Connection connection = dataSource.getConnection()) {
            String sqlScript = "DELETE FROM user WHERE id=";
            final PreparedStatement preparedStatement = connection.prepareStatement(sqlScript + id);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                isSucceed = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSucceed;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setLastname(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("passwordhash"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        return user;
    }

    public List<User> getAllUsers() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sqlScript = "SELECT * FROM user";
            ResultSet result = statement.executeQuery(sqlScript);
            List<User> allUsers = new ArrayList<>();
            while (result.next()) {
                User user = getUserFromResultSet(result);
                allUsers.add(user);
            }
            return allUsers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByLogin(String email, String password) {
        User user = new User();
        try (Connection connection = dataSource.getConnection()) {
            String sqlScript = "SELECT * FROM user WHERE email=? AND passwordhash=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

