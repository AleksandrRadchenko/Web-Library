package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private DataSource dataSource;

    public BookDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        return books;
    }

    public List<BookInstance> getAllBookInstances() {
        List<BookInstance> bookInstances = new ArrayList<>();

        return bookInstances;
    }

    // tested
    public int getBookId(String title, String author, int year) {
        int id = -1;

        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " +
                    "book WHERE author=? AND title=? AND year=?");
            preparedStatement.setString(1, author);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, year);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int getFreeBookInstanceId(int bookId) {
        // TODO look through the book table and if the requested book have at least one free instance return it's id
        return -1;
    }

    public void addNewBook(String title, String author, int year) {
        // TODO add new book in table book_instance and in table book if necessary (with creating unique book_instanceid)
    }

    public void addNewBookInstance(String title, String author, int year) {

    }
}