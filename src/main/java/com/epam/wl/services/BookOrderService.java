package com.epam.wl.services;

import com.epam.wl.DBHelper;
import com.epam.wl.dao.BookOrderDAO;
import com.epam.wl.entities.BookOrder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookOrderService {
    private static EmbeddedDatabase dataSource = DBHelper.getEmbeddedDatabase();;
    private static BookOrderDAO bookOrderDAO = new BookOrderDAO(dataSource);

    public static List<BookOrder> getAll() {
        List<BookOrder> result = new ArrayList<>();
        try {
            result = bookOrderDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
