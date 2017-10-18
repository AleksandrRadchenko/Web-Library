package com.epam.wl.executor;

import com.epam.wl.db.JdbcConnector;
import lombok.Getter;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Executor {
    public static int connectionCount;
    private static Executor instance;
    private static DataSource dataSource;
    // Selector for prod and test dataSource. Switch in jdbc.properties file.
    @Getter
    static String mode = "production";
    private static final DataSource postgresDataSource = JdbcConnector.getDataSource();
    private static DataSource testDataSource;

    static {
        final Properties properties = new Properties();
        try (InputStream resourceAsStream = JdbcConnector.class.getResourceAsStream("/jdbc.properties")) {
            properties.load(resourceAsStream);
            mode = properties.getProperty("mode");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("test".equals(mode)) testDataSource = JdbcConnector.getTestDataSource();
    }

    private Executor() {
    }

    public static void resetTestDataSource() {
        if ("test".equals(mode)) dataSource = JdbcConnector.getNewTestDataSource();
    }

    public static synchronized Executor getInstance() {
        if ("test".equals(mode)) dataSource = testDataSource;
        else dataSource = postgresDataSource;
        if (instance == null)
            instance = new Executor();
        return instance;
    }

    public void executeUpdate(final String update, Object... args) throws SQLException {
        try (final Connection con = dataSource.getConnection();
             final PreparedStatement stmt = con.prepareStatement(update)) {//доходит, ок
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
        try (final Connection con = dataSource.getConnection();
             final PreparedStatement stmt = con.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].getClass() == Integer.class) {
                    stmt.setInt(i + 1, (Integer) args[i]);
                } else {
                    stmt.setString(i + 1, (String) args[i]);
                }
            }
            return handler.handle(stmt.executeQuery());
        }
    }
}
