package com.epam.wl.dao.user_order_handlers;

import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInstanceIdHandler implements ResultHandler<Integer> {
    @Override
    public Integer handle(ResultSet resultSet) throws SQLException {
//        System.out.println(resultSet.next());
//        while (resultSet.next()){
//            System.out.println();
//        }
        if (resultSet.next()) {
            return resultSet.getInt("book_instance.id");
        }

        /*throw new NullPointerException("No free book instance in library");*/
        return 100500;
    }
}
