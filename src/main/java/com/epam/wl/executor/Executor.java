package com.epam.wl.executor;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RequiredArgsConstructor
public class Executor {
    private final DataSource dataSource;

    public void executeUpdate(final String update) throws SQLException {
        try (Statement stmt = dataSource.getConnection().createStatement()) {
            stmt.execute(update);
        }
    }

    public <T> T executeQuery(final String query, final ResultHandler<T> handler) throws SQLException {
        try (Statement stmt = dataSource.getConnection().createStatement()) {
            stmt.execute(query);
            final ResultSet result = stmt.getResultSet();
            T value = handler.handle(result);
            result.close();
            return value;
        }
    }
}
