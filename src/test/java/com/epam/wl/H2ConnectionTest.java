package com.epam.wl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class H2ConnectionTest {
    private Connection dbConnection;

    @BeforeEach
    public void setup() throws SQLException {
        this.dbConnection = DriverManager.getConnection("jdbc:h2:./test",
                "sa", "");
    }

    @Test
    public void ConnectionTest() throws SQLException {
        List<Integer> expectedIdList = new ArrayList<>();
        expectedIdList.add(1);
        expectedIdList.add(2);
        List<String> expectedNameList = new ArrayList<>();
        expectedNameList.add("Hello");
        expectedNameList.add("World");

        Statement st = dbConnection.createStatement();
        st.execute("DROP TABLE IF EXISTS TEST");
        st.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
        st.execute("INSERT INTO TEST VALUES(1, 'Hello');");
        st.execute("INSERT INTO TEST VALUES(2, 'World');");
        ResultSet result = st.executeQuery("SELECT * FROM TEST");

        List<Integer> actualIdLIst = new ArrayList();
        List<String> actualNameList = new ArrayList();
        while (result.next()) {
            int id = result.getInt("ID");
            String name = result.getString("NAME");
            actualIdLIst.add(id);
            actualNameList.add(name);
        }
        assertIterableEquals(expectedIdList, actualIdLIst);
        assertIterableEquals(expectedNameList, actualNameList);
    }

    @AfterEach
    public void tearDown() throws Exception {
        dbConnection.close();
    }
}
