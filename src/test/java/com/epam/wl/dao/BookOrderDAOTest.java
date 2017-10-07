package com.epam.wl.dao;

import lombok.SneakyThrows;
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
        printResultSet(result);

    }

    @Test
    void createEnumsInH2() throws SQLException {
        Statement st = dbConnection.createStatement();
        st.executeQuery("SELECT id, bookid FROM book_order");
        printResultSet(st.getResultSet());
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

    @SneakyThrows
    private void printResultSet(ResultSet set) {
        int row = 1;
        while (set.next()) {
            System.out.print(row++ + " : ");
            ResultSetMetaData metaData = set.getMetaData();
            for (int i = 1; i < metaData.getColumnCount(); i++) {
                Object o = set.getObject(i);
                System.out.print(metaData.getColumnName(i) + " = " + o.toString() + "; ");
            }
            int lastColumn = metaData.getColumnCount();
            System.out.println(metaData.getColumnName(lastColumn) + " = " + set.getObject(lastColumn));
        }
    }
}