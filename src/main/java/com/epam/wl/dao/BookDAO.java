package com.epam.wl.dao;

import com.epam.wl.entities.Book;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private DataSource dataSource;

    public BookDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // test created
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        return books;
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

    public int getFreeInstanceId(int bookId) {
        // TODO look through the book table and if the requested book have at least one free instance return it's id
        return -1;
    }

    // new books coming in library
    public void addNewBook(String title, String author, int year) {
        // TODO add new book in table book_instance and in table book if necessary (with creating unique book_instanceid)
    }

    public void addNewBookInstance(String title, String author, int year) {

    }

    /*// it is necessary then user doesn't give back the book
    public boolean removeBook(int id, String title, String author, int year) {
        // TODO remove book from book_instance and from book table if necessary (if there are no one instance anymore)
        return true;
    }
    }*/
}