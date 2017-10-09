package com.epam.wl.dao;

import com.epam.wl.dao.user_order_handlers.BookIdHandler;
import com.epam.wl.dao.user_order_handlers.BookInstanceIdHandler;
import com.epam.wl.dao.user_order_handlers.BookInstanceListHandler;
import com.epam.wl.dao.user_order_handlers.BookListHandler;
import com.epam.wl.entities.Book;
import com.epam.wl.entities.BookInstance;
import com.epam.wl.executor.Executor;
import com.epam.wl.executor.ResultHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class BookDAO {
    private Executor executor;
    private final ResultHandler<List<Book>> bookListHandler;
    private final ResultHandler<List<BookInstance>> bookInstanceListHandler;
    private final ResultHandler<Integer> bookIdHandler;
    private final ResultHandler<Integer> bookInstanceIdHandler;

    public BookDAO(DataSource dataSource) {
        this.executor = new Executor(dataSource);
        this.bookListHandler = new BookListHandler();
        this.bookInstanceListHandler = new BookInstanceListHandler();
        this.bookIdHandler = new BookIdHandler();
        this.bookInstanceIdHandler = new BookInstanceIdHandler();
    }

    // tested
    public List<Book> getAllBooks() throws SQLException {
        String query = "SELECT * FROM book";
        return executor.executeQuery(query, bookListHandler);
    }

    // tested
    public List<BookInstance> getAllBookInstances() throws SQLException {
        String query = "SELECT * FROM book_instance";
        return executor.executeQuery(query, bookInstanceListHandler);
    }

    // tested
    public int getBookId(String author, String title, int year) throws SQLException {
        String query = String.format("SELECT * FROM book WHERE author='%s' AND title='%s' AND year=%d",
                author, title, year);
        return executor.executeQuery(query, bookIdHandler);
    }

    public int getFreeBookInstanceId(int bookId) throws SQLException {
        /*String query = String.format("SELECT book_instance LEFT JOIN book_order " +
                "ON book_instance.id=book_order.book_instanceid " +
                "WHERE book_instance.bookid=%d AND book_order.id=NULL", bookId);*/

        String query = String.format("SELECT * FROM book_instance LEFT JOIN book_order " +
                "ON book_instance.id=book_order.book_instanceid WHERE " +
                "book_instance.bookid=1");

        return executor.executeQuery(query, bookInstanceIdHandler);
    }
    /*
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
    }*/



    /*
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
    }*/
}