package com.epam.wl.services;

import com.epam.wl.DBHelper;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.Executor;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserOrderService {
    private static UserOrderService instance;

    private UserOrderService(){}

    public static UserOrderService getInstance(){
        if (instance == null) {
            instance = new UserOrderService();
        }
        return instance;
    }
    public void setUserOrderStatus(int book_orderid, UserOrderStatus closed) {
        DataSource dataSource = DBHelper.getEmbeddedDatabase();
        Executor executor = new Executor(dataSource);
        String UPDATE_QUERY = "UPDATE user_order SET status='CLOSED' WHERE id=?";
        try {
            executor.executeUpdate(UPDATE_QUERY, String.valueOf(book_orderid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
