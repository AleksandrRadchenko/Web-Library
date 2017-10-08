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
            int userOrderID = resultSet.getInt("id");
            int bookID = resultSet.getInt("bookid");
            int userID = resultSet.getInt("userid");
            UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
            resultUserOrderList.add(new UserOrder(userOrderID, bookID, userID, status));
        }
        return resultUserOrderList;
    }
}
