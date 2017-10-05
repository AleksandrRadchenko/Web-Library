package com.epam.wl.core;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectH2 {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:./test",
                    "sa", "");
            Statement st = conn.createStatement();;
            st.execute("DROP TABLE IF EXISTS TEST");
            st.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
            st.execute("INSERT INTO TEST VALUES(1, 'Hello');");
            st.execute("INSERT INTO TEST VALUES(2, 'World');");

            ResultSet result;
            result = st.executeQuery("SELECT * FROM TEST");
            while (result.next()) {
                String name = result.getString("NAME");
                System.out.println(result.getString("ID")+" "+name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
