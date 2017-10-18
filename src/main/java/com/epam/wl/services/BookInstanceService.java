package com.epam.wl.services;

import com.epam.wl.dao.BookInstanceDAO;
import com.epam.wl.entities.BookInstance;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.Optional;

@Log4j2
public class BookInstanceService {
    private static BookInstanceService instance;
    private final BookInstanceDAO bookInstanceDAO = BookInstanceDAO.getInstance();

    private BookInstanceService(){}

    public static synchronized BookInstanceService getInstance() {
        if (instance == null) {
            instance = new BookInstanceService();
            log.info("BookInstanceService instance created");
        }
        log.info("BookInstanceService instance supplied");
        return instance;
    }

    public BookInstance getById(int book_instanceid) throws SQLException {
        Optional<BookInstance> oBookInstance = bookInstanceDAO.getById(book_instanceid);
        if (!oBookInstance.isPresent()) throw new SQLException("There is no such book_instance for id = " + book_instanceid);
        else return oBookInstance.get();
    }
}
