package com.epam.wl.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcConnector {
    private static JdbcConnector instance;
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
                Properties properties = new Properties();
                try (InputStream resourceAsStream = JdbcConnector.class.getResourceAsStream("/jdbc.properties")) {
                    properties.load(resourceAsStream);
                    String url = properties.getProperty("url");
                    String userName = properties.getProperty("userName");
                    String password = properties.getProperty("password");
                    instance = new JdbcConnector();
                    instance.dataSource = new DriverManagerDataSource(url, userName, password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance.dataSource;
    }
}
