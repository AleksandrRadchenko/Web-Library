package com.epam.wl.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

class BookOrderDAOTest {
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        this.connection =
                     DriverManager.getConnection(
                             "jdbc:postgresql://localhost:5432/postgres",
                             "postgres",
                             "password");
    }

    @Test
    void create() throws SQLException {
        Statement st = connection.createStatement();
        //st.execute("CREATE SCHEMA testschema");
        st.execute("SET SCHEMA 'testschema'");
        st.execute("DROP TABLE IF EXISTS TEST");
        st.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
        st.execute("INSERT INTO TEST VALUES(1, 'Hello');");
        st.execute("INSERT INTO TEST VALUES(2, 'World');");
        ResultSet result = st.executeQuery("SELECT * FROM TEST");
        while (result.next()) {
            int id = result.getInt("ID");
            String name = result.getString("NAME");
            System.out.println(id);
            System.out.println(name);
        }
    }

    @Test
    void getAll() {
    }

    @Test
    void getNew() {
    }

    @AfterEach
    void tearDown() throws SQLException {
        this.connection.close();
    }
}