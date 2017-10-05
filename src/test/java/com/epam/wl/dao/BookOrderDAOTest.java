package com.epam.wl.dao;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookOrderDAOTest {
    private Connection dbConnection;

    @BeforeEach
    void setUp() throws SQLException {
        dbConnection = DriverManager.getConnection("jdbc:h2:mem:testcase");
    }

    @Test
    void create() throws SQLException {

        Statement st = dbConnection.createStatement();
        st.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
        st.execute("INSERT INTO TEST VALUES(1, 'Hello');");
        st.execute("INSERT INTO TEST VALUES(2, 'World');");
        dbConnection.commit();
        ResultSet result = st.executeQuery("SELECT * FROM TEST");
        while (result.next()) {
            int id = result.getInt("ID");
            String name = result.getString("NAME");
//            assertEquals()
            System.out.print("ID = " + id);
            System.out.println(", Name = " + name);
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
        dbConnection.close();
    }
}