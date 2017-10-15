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

    private static final String ADD_USER_SCRIPT =
            "INSERT INTO \"user\"(name, lastname, email, passwordhash, role) VALUES(?,?,?,?,?)";
    private static final String UPDATE_USER_SCRIPT =
            "UPDATE \"user\" SET name=?, lastname=?, email=?, passwordhash=?, role =? WHERE id=?";
    private static final String DELETE_USER_BYID_SCRIPT =
            "DELETE FROM \"user\" WHERE id=?";
    private static final String GETALL_USERS_SCRIPT =
            "SELECT * FROM \"user\"";
    private static final String GET_USER_BYID_SCRIPT = "SELECT * FROM \"user\" WHERE id=?";
//            "SELECT user.id, user.name, user.lastname, " +
//            "user.email, user.passwordhash, user.role FROM user WHERE user.id = ?";
    //"SELECT * FROM \"user\" WHERE id=?";
//            "SELECT \"user\".id, \"user\".name, \"user\".lastname, \"user\".email, \"user\".passwordhash, \"user\".role" +
//                    "FROM \"user\" WHERE \"user\".id = ?";//"SELECT * FROM user WHERE id=?";
    private static final String GET_USER_BY_EMAIL_PASS_SCRIPT =
            "SELECT * FROM \"user\" WHERE email=? AND passwordhash=?";
    private static final String GET_USER_SCRIPT_By_NAME_LASTRNAME_SCRIPT =
            "SELECT * FROM \"user\" WHERE name=? AND lastname=?";

//    "SELECT user.name, user.lastname, user.email, user.passwordhash, user.role WHERE user.id = ?";


    public UserDAO(DataSource dataSource) {
        this.executor = new Executor(dataSource);
        this.userListHandler = new UserListHandler();
        this.userOneHandler = new UserOneHandler();
    }

    public void addUser(String name, String lastname, String email, String passwordHash, UserRole userRole) throws SQLException {
        executor.executeUpdate(ADD_USER_SCRIPT, name, lastname, email, passwordHash, userRole.toString());
    }

    public void updateUser(int id, String name, String lastname, String email, String passwordHash, UserRole userRole) throws SQLException {
        executor.executeUpdate(UPDATE_USER_SCRIPT, name, lastname, email, passwordHash, userRole.toString(), String.valueOf(id));
    }

    public void deleteUserById(int id) throws SQLException {
        executor.executeUpdate(DELETE_USER_BYID_SCRIPT, String.valueOf(id));
    }

    public List<User> getAllUsers() throws SQLException {
        return executor.executeQuery(GETALL_USERS_SCRIPT, userListHandler);
    }

    public Optional<User> getUserByID(int id) throws SQLException {
        return executor.executeQuery(GET_USER_BYID_SCRIPT, userOneHandler, String.valueOf(id));
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) throws SQLException {
        return executor.executeQuery(GET_USER_BY_EMAIL_PASS_SCRIPT, userOneHandler, email, password);
    }

    public Optional<User> getUserByNameAndLastName(String name, String lastName) throws SQLException {
        return executor.executeQuery(GET_USER_SCRIPT_By_NAME_LASTRNAME_SCRIPT, userOneHandler, name, lastName);
    }
}

