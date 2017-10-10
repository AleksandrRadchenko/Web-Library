package com.epam.wl.dao;

import com.epam.wl.dao.user_handlers.UserListHandler;
import com.epam.wl.dao.user_handlers.UserOneHandler;
import com.epam.wl.entities.User;
import com.epam.wl.enums.UserRole;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    private Executor executor;
    private final ResultHandler<Optional<User>> userOneHandler;
    private final ResultHandler<List<User>> userListHandler;

    public UserDAO(DataSource dataSource) {
        this.executor = new Executor(dataSource);
        this.userListHandler = new UserListHandler();
        this.userOneHandler = new UserOneHandler();
    }

    public void addUser(String name, String lastname, String email, String passwordHash, UserRole userRole) throws SQLException {
        String sqlScript = "INSERT INTO user(name, lastname, email, passwordhash, role) VALUES(?,?,?,?,?)";
        executor.executeUpdate(sqlScript, name, lastname, email, passwordHash, userRole.toString());
    }

    public void updateUser(int id, String name, String lastname, String email, String passwordHash, UserRole userRole) throws SQLException {
        String sqlScript = "UPDATE user SET name=?, lastname=?, email=?, passwordhash=?, role =? WHERE id=?";
        executor.executeUpdate(sqlScript, name, lastname, email, passwordHash, userRole.toString(), String.valueOf(id));
    }

    public void deleteUserById(int id) throws SQLException {
        String sqlScript = "DELETE FROM user WHERE id=?";
        executor.executeUpdate(sqlScript, String.valueOf(id));
    }

    public List<User> getAllUsers() throws SQLException {
        String sqlScript = "SELECT * FROM user";
        return executor.executeQuery(sqlScript, userListHandler);
    }

    public Optional<User> getUserByID(int id) throws SQLException {
        String sqlScriptGetById = "SELECT * FROM user WHERE id=?";
        return executor.executeQuery(sqlScriptGetById, userOneHandler, String.valueOf(id));
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) throws SQLException {
        String sqlScriptByLoginAndPassword = "SELECT * FROM user WHERE email=? AND passwordhash=?";
        return executor.executeQuery(sqlScriptByLoginAndPassword, userOneHandler, email, password);
    }

    public Optional<User> getUserByNameAndLastName(String name, String lastName) throws SQLException {
        String sqlScriptByName = "SELECT * FROM user WHERE name=? AND lastname=?";
        return executor.executeQuery(sqlScriptByName, userOneHandler, name, lastName);
    }
}

