package com.epam.wl.dao.user_order_handlers;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserOrderOneHandler implements ResultHandler<Optional<UserOrder>>{
    @Override
    public Optional<UserOrder> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        int userOrderID = resultSet.getInt("id");
        int bookID = resultSet.getInt("bookid");
        int userID = resultSet.getInt("userid");
        UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
        return Optional.of(new UserOrder(userOrderID, bookID, userID, status));
    }
}
