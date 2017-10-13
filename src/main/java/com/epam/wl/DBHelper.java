package com.epam.wl;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public interface DBHelper {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db = builder
            .setType(EmbeddedDatabaseType.H2)
            .setScriptEncoding("UTF-8")
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();

    static EmbeddedDatabase getEmbeddedDatabase() {
        return db;
    }

    static EmbeddedDatabase getNewEmbeddedDatabase() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();
    }

    /**
     * Prints to stOut result set as a table
     *
     * @param set ResultSet to be printed
     * @throws SQLException
     */
    static void printResultSet(ResultSet set) throws SQLException {
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
