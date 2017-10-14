package com.epam.wl.dao.user_order_handlers;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserOrderListHandler implements ResultHandler<List<UserOrder>> {
    @Override
    public List<UserOrder> handle(ResultSet resultSet) throws SQLException {
        List<UserOrder> resultUserOrderList = new ArrayList();
        while (resultSet.next()) {
            int userOrderID = resultSet.getInt("user_order_id");
            int userID = resultSet.getInt("user_id");
            String userName = resultSet.getString("user_name");
            String userLastname = resultSet.getString("user_lastname");
            String userEmail = resultSet.getString("user_email");
            String bookTitle = resultSet.getString("title");
            String bookAuthor = resultSet.getString("author");
            int bookYear = resultSet.getInt("year");
            UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
            resultUserOrderList.add(new UserOrder(userOrderID, userID, userName, userLastname, userEmail, bookTitle, bookAuthor, bookYear, status));
        }
        return resultUserOrderList;
    }
}