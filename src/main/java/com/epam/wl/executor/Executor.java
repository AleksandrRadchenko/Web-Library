package com.epam.wl.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(String update) throws SQLException {
        final Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
    }

    public <T> T executeQuery(String query, ResultHandler<T> handler) throws SQLException {
        final Statement stmt = connection.createStatement();
        stmt.execute(query);
        final ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();

        return value;
    }

}
