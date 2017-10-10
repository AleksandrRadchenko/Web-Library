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
        String sqlScript = String.format("INSERT INTO user(name, lastname, email, passwordhash, role) VALUES('%s','%s','%s','%s','%s')",
                name, lastname, email, passwordHash, userRole);
        executor.executeUpdate(sqlScript);
    }

    public void updateUser(int id, String name, String lastname, String email, String passwordHash, UserRole userRole) throws SQLException {
        String sqlScript = String.format("UPDATE user SET name='%s', lastname='%s', email='%s', passwordhash='%s', role ='%s' WHERE id=%d",
                name, lastname, email, passwordHash, userRole, id);
        executor.executeUpdate(sqlScript);
    }

    public void deleteUserById(int id) throws SQLException {
        String sqlScript = String.format("DELETE FROM user WHERE id=%d", id);
        executor.executeUpdate(sqlScript);
    }

    public List<User> getAllUsers() throws SQLException {
        String sqlScript = "SELECT * FROM user";
        return executor.executeQuery(sqlScript, userListHandler);
    }

    public Optional<User> getUserByID(int id) throws SQLException {
        String sqlScriptGetById = String.format("SELECT * FROM user WHERE id=%d", id);
        return executor.executeQuery(sqlScriptGetById, userOneHandler);
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) throws SQLException {
        String sqlScriptByLoginAndPassword = String.format("SELECT * FROM user WHERE email='%s' AND passwordhash='%s'", email, password);
        return executor.executeQuery(sqlScriptByLoginAndPassword, userOneHandler);
    }

    public Optional<User> getUserByNameAndLastName(String name, String lastName) throws SQLException {
        String sqlScriptByName = String.format("SELECT * FROM user WHERE name='%s' AND lastname='%s'", name, lastName);
        return executor.executeQuery(sqlScriptByName, userOneHandler);
    }
}

