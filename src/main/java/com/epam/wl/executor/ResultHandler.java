package com.epam.wl.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(final ResultSet resultSet) throws SQLException;
}
