package com.epam.wl.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

class BookOrderDAOTest {
    private Connection dbConnection;

    @BeforeEach
    void setUp() throws SQLException {
        dbConnection = DriverManager.
                getConnection("jdbc:h2:mem:testDB;" +
                        "INIT=RUNSCRIPT FROM 'classpath:H2DBinit.sql'\\;" +
                        "RUNSCRIPT FROM 'classpath:H2DBdata.sql'");
    }

    @Test
    void getUserById() throws SQLException {
        Statement st = dbConnection.createStatement();
        ResultSet result = st.executeQuery("SELECT id, bookid, orderid, option FROM book_order");
        //bookid, orderid, option) VALUES (1, 2, 'SUBSCRIPTION');
        while (result.next()) {
            int id = result.getInt("id");
            int bookid = result.getInt("bookid");
            int orderid = result.getInt("orderid");
            String option = result.getString("option");
            System.out.print("ID = " + id);
            System.out.print(", bookid = " + bookid);
            System.out.print(", orderid = " + orderid);
            System.out.println(", option = " + option);
        }

    }

    @Test
    void createTest() throws SQLException {

        BookOrderDAO bookOrderDAO = new BookOrderDAO(dbConnection);

//        bookOrderDAO.create();

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