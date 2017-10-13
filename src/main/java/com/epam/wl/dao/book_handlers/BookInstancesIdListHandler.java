package com.epam.wl.dao.book_handlers;

import com.epam.wl.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInstancesIdListHandler implements ResultHandler<List<Integer>> {
    @Override
    public List<Integer> handle(ResultSet resultSet) throws SQLException {
        final List<Integer> bookInstancesId = new ArrayList<>();

        while (resultSet.next()) {
            bookInstancesId.add(resultSet.getInt("id"));
        }

        return bookInstancesId;
    }
}
