package com.epam.wl.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcConnector {
    private static JdbcConnector instance;
    private static EmbeddedDatabase testInstance;
    private DataSource dataSource;

    private JdbcConnector() {
    }

    public static DataSource getDataSource() {
        if (instance == null) {
            synchronized (JdbcConnector.class) {
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                final Properties properties = new Properties();
                try (InputStream resourceAsStream = JdbcConnector.class.getResourceAsStream("/jdbc.properties")) {
                    properties.load(resourceAsStream);
                    final String url = properties.getProperty("url");
                    final String userName = properties.getProperty("userName");
                    final String password = properties.getProperty("password");
                    instance = new JdbcConnector();
                    instance.dataSource = new DriverManagerDataSource(url, userName, password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance.dataSource;
    }

    public static EmbeddedDatabase getTestDataSource() {
        if (testInstance == null) {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            testInstance = builder
                    .setType(EmbeddedDatabaseType.H2)
                    .setScriptEncoding("UTF-8")
                    .addScript("H2DBinit.sql")
                    .addScript("H2DBdata.sql")
                    .build();
        }
        return testInstance;
    }

    public static EmbeddedDatabase getNewTestDataSource() {
        testInstance.shutdown();
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        testInstance = builder
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();
        return testInstance;
    }
}
