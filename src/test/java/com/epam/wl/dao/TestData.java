package com.epam.wl.dao;

import com.epam.wl.entities.*;
import com.epam.wl.enums.BookOption;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.enums.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface TestData {
    Book b1 = new Book(1, "Петр Иванов", "Азбука", 1954);
    Book b2 = new Book(2, "Herbert Schildt", "Java: A Beginner's Guide, Sixth Edition", 2014);
    Book b3 = new Book(3, "Bruce Eckel", "Thinking in Java (4th Edition)", 2016);
    Book b4 = new Book(4, "Лев Толстой", "Война и мир", 1978);
    List<Book> books = new ArrayList<>(Arrays.asList(b1, b2, b3, b4));
    Book b5 = new Book(5, "Jerome David Salinger","The Catcher in the Rye", 2012);

    BookInstance bi1 = new BookInstance(1, b1);
    BookInstance bi2 = new BookInstance(2, b1);
    BookInstance bi3 = new BookInstance(3, b1);
    BookInstance bi4 = new BookInstance(4, b1);
    BookInstance bi5 = new BookInstance(5, b2);
    BookInstance bi6 = new BookInstance(6, b2);
    BookInstance bi7 = new BookInstance(7, b2);
    BookInstance bi8 = new BookInstance(8, b2);
    BookInstance bi9 = new BookInstance(9, b2);
    BookInstance bi10 = new BookInstance(10, b4);
    BookInstance bi11 = new BookInstance(11, b3);
    List<BookInstance> bookInstances = new ArrayList<>(Arrays.asList(bi1,bi2,bi3,bi4,bi5,bi6,bi7,bi8,bi9,bi10,bi11));
    BookInstance bi12 = new BookInstance(12, b4);

    User u1 = new User(1, "Иван", "Иванов", "ivan@ivan.ru", "fdfsdcdzc", UserRole.USER);
    User u2 = new User(2, "Федор", "Федоров", "fedor@ivan.ru", "fdfsdcdrfdsfzc", UserRole.USER);
    User u3 = new User(3, "Петр", "Петров", "petr@ivan.ru", "fdfsdcdssdfdzc", UserRole.USER);
    User u4 = new User(4, "Семен", "Семенов", "semen@ivan.ru", "fdfsdcdssdfdzc", UserRole.LIBRARIAN);
    List<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
    User u5 = new User(5, "Luke", "Skywalker", "1@gmail.com", "passHash", UserRole.USER);

    UserOrder uo1 = new UserOrder(1, u1, b1, UserOrderStatus.IN_PROGRESS);
    UserOrder uo2 = new UserOrder(2, u2, b2, UserOrderStatus.IN_PROGRESS);
    UserOrder uo3 = new UserOrder(3, u3, b3, UserOrderStatus.IN_PROGRESS);
    UserOrder uo4 = new UserOrder(4, u4, b4, UserOrderStatus.IN_PROGRESS);
    List<UserOrder> userOrders = new ArrayList<>(Arrays.asList(uo1, uo2, uo3, uo4));
    UserOrder uo5 = new UserOrder(5, u3, b1, UserOrderStatus.NEW);

    BookOrder bo1 = new BookOrder(1, bi4, uo1, BookOption.SUBSCRIPTION);
    BookOrder bo2 = new BookOrder(2, bi6, uo2, BookOption.SUBSCRIPTION);
    BookOrder bo3 = new BookOrder(3, bi11, uo3, BookOption.READING_ROOM);
    BookOrder bo4 = new BookOrder(4, bi10, uo4, BookOption.READING_ROOM);
    List<BookOrder> bookOrders = new ArrayList<>(Arrays.asList(bo1, bo2, bo3, bo4));
    BookOrder bo5 = new BookOrder(5, bi1, uo1, BookOption.SUBSCRIPTION);
}
