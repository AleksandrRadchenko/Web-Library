package com.epam.wl.services;

import com.epam.wl.DBHelper;
import com.epam.wl.dao.BookOrderDAO;
import com.epam.wl.entities.BookOrder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookOrderService {
    private static BookOrderService instance;
    private DataSource dataSource = DBHelper.getEmbeddedDatabase();;
    private BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance(dataSource);

    private BookOrderService(){}

    public static synchronized BookOrderService getInstance(){
        if (instance == null)
            instance = new BookOrderService();
        return instance;
    }

    public List<BookOrder> getAll() {
        List<BookOrder> result = new ArrayList<>();
        try {
            result = bookOrderDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<BookOrder> getByUserId(int userId) {
        List<BookOrder> result = new ArrayList<>();
        try {
            result = bookOrderDAO.getByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
