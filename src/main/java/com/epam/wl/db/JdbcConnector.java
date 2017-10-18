package com.epam.wl.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcConnector {
    private static ComboPooledDataSource instance;
    private static EmbeddedDatabase testInstance;

    private JdbcConnector() {
    }

    public static DataSource getDataSource() {
        if (instance == null) {
            synchronized (JdbcConnector.class) {
                final Properties properties = new Properties();
                try (InputStream resourceAsStream = JdbcConnector.class.getResourceAsStream("/jdbc.properties")) {
                    properties.load(resourceAsStream);
                    final String url = properties.getProperty("url");
                    final String userName = properties.getProperty("userName");
                    final String password = properties.getProperty("password");
                    instance = new ComboPooledDataSource();
                    instance.setDriverClass("org.postgresql.Driver");
                    instance.setJdbcUrl(url);
                    instance.setUser(userName);
                    instance.setPassword(password);
                    instance.setMaxPoolSize(50);
                } catch (IOException|PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
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
