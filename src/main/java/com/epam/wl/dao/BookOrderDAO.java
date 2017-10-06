package com.epam.wl.dao;

import com.epam.wl.entities.BookInstance;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.Order;
import com.epam.wl.enums.BookOptions;
import lombok.RequiredArgsConstructor;
//import com.epam.wl.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookOrderDAO {
//    private Executor executor;
    private final Connection connection;

    void create(final BookInstance bookInstance, final Order userOrder, final BookOptions bookOption) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, bookInstance.getId());
            preparedStatement.setInt(2, userOrder.getId());
            preparedStatement.setString(3, bookOption.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }


    public List<BookOrder> getAll(){
        List<BookOrder> all = new ArrayList<>();

        return all;
    }

    public BookOrder getById(final int id) {
        BookOrder result = null;
//        ResultHandler<BookOrder> handler = new ResultHandler
//        executor.execQuery("SELECT * from BookOrder WHERE id = " + id);

        return result;
    }
}
