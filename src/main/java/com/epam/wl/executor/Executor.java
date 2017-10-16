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

    public void executeUpdate(final String update, Object... args) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(update)) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].getClass() == Integer.class) {
                    stmt.setInt(i + 1, (Integer) args[i]);
                } else {
                    stmt.setString(i + 1, (String) args[i]);
                }
            }
            stmt.executeUpdate();
        }
    }

    public <T> T executeQuery(final String query, final ResultHandler<T> handler, Object... args) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].getClass() == Integer.class) {
                    stmt.setInt(i + 1, (Integer) args[i]);
                } else {
                    stmt.setString(i + 1, (String) args[i]);
                }
            }
            final ResultSet result = stmt.executeQuery();
            T value = handler.handle(result);
            result.close();
            return value;
        }
    }
}
