package com.epam.wl.dao;

import com.epam.wl.entities.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BookDAOTest {
    private EmbeddedDatabase dataSource;
    private BookDAO bookDAO;

    /**
     * Initializes database before each call test method
     */
    @BeforeEach
    void initDatabase() {
        dataSource = getEmbeddedDatabase();
        bookDAO = new BookDAO(dataSource);
    }

    /**
     * Creates database depending on sql scripts from resources package
     *
     * @return created embedded database
     */
    private EmbeddedDatabase getEmbeddedDatabase() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        final EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();
        return db;
    }

    /**
     * Shuts down database after each call test method
     */
    @AfterEach
    void dropDatabase() {
        dataSource.shutdown();
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Петр Иванов", "Азбука", 1954));
        books.add(new Book(2, "Herbert Schildt", "Java: A Beginner's Guide, Sixth Edition", 2014));
        books.add(new Book(3, "Bruce Eckel", "	Thinking in Java (4th Edition)", 2016));
        books.add(new Book(4, "Лев Толстой", "Война и мир", 1978));

        assertThat(books, is(bookDAO.getAllBooks()));
    }

    @Test
    void testGetBookId() {
        int id = 4;
        String author = "Лев Толстой";
        String title = "Война и мир";
        int year = 1978;

        assertThat(id, is(bookDAO.getBookId(title, author, year)));
    }


}
