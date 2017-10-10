package com.epam.wl.executor;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RequiredArgsConstructor
public class Executor {
    private final DataSource dataSource;

    public void executeUpdate(final String update, String... args) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(update)) {
            for (int i = 0; i < args.length; i++)
                stmt.setString(i + 1, args[i]);
            stmt.executeUpdate();
        }
    }

    public <T> T executeQuery(final String query, final ResultHandler<T> handler, String... args) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(query)) {
            for (int i = 0; i < args.length; i++)
                stmt.setString(i + 1, args[i]);
            final ResultSet result = stmt.executeQuery();
            T value = handler.handle(result);
            result.close();
            return value;
        }
    }
}
