package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import com.sun.org.apache.regexp.internal.RE;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BookDAO {
    private DataSource dataSource;

    public BookDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM book");

            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"), resultSet.getString("author"),
                        resultSet.getString("title"), resultSet.getInt("year")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<BookInstance> getAllBookInstances() {
        List<BookInstance> bookInstances = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM book_instance");

            while (resultSet.next()) {
                bookInstances.add(new BookInstance(resultSet.getInt("id"),
                        resultSet.getInt("bookid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookInstances;
    }

    public int getBookId(String author, String title, int year) {
        int id = -1;

        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM book WHERE author=? AND title=? AND year=?");
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
        int id = -1;

        try (Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet bookInstancesSet = statement.executeQuery(
                    "SELECT * FROM book_instance WHERE bookid=" + bookId);

            List<Integer> bookInstancesId = new ArrayList<>();

            while (bookInstancesSet.next()) {
                bookInstancesId.add(bookInstancesSet.getInt("id"));
            }

            final Statement secondStatement = connection.createStatement();
            final ResultSet bookOrdersSet = secondStatement.executeQuery("SELECT * FROM book_order");

            while (bookOrdersSet.next()) {
                if (bookInstancesId.contains(bookOrdersSet.getInt("book_instanceid"))) {
                    bookInstancesId.remove((Integer) bookOrdersSet.getInt("book_instanceid"));
                }
            }

            Collections.sort(bookInstancesId); // to sort or not to sort? seems like not

            if (!bookInstancesId.isEmpty()) {
                id = bookInstancesId.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void addNewBook(String author, String title, int year) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book(author, title, year) VALUES (?, ?, ?)");
            preparedStatement.setString(1, author);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, year);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewBookInstance(String author, String title, int year) {
        try (Connection connection = dataSource.getConnection()) {
            int bookId = getBookId(author, title, year);

            if (bookId == -1) {
                addNewBook(author, title, year);
                bookId = getBookId(author, title, year);
            }

            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book_instance(bookid) VALUES (?)");
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}