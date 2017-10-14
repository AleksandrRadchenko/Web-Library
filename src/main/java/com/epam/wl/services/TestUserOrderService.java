package com.epam.wl.services;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.enums.UserOrderStatus;

import java.util.*;

//Instead of DB connection. Just for example

public class TestUserOrderService {
    public static List<UserOrder> getOrders() {
        List<UserOrder> list = new ArrayList<>();
        list.add(new UserOrder(
                1, 1, "Иван", "Иванов", "ivan@ivan.ru", "Азбука",
                "Петр Иванов", 1954, UserOrderStatus.IN_PROGRESS));
        list.add(new UserOrder(
                2, 2, "Федор", "Федоров", "fedor@ivan.ru", "Java: A Beginner's Guide, Sixth Edition",
                "Herbert Schildt", 2014, UserOrderStatus.IN_PROGRESS));
        list.add(new UserOrder(
                3, 3, "Петр", "Петров", "petr@ivan.ru", "Thinking in Java (4th Edition)",
                "Bruce Eckel", 2016, UserOrderStatus.IN_PROGRESS));
        list.add(new UserOrder(
                4, 4, "Семен", "Семенов", "semen@ivan.ru", "Война и мир",
                "Лев Толстой", 1978, UserOrderStatus.IN_PROGRESS));
        list.add(new UserOrder(
                5, 1, "Иван", "Иванов", "ivan@ivan.ru", "Thinking in Java (4th Edition)",
                "Bruce Eckel", 2016, UserOrderStatus.NEW));
        return list;
    }

    public static List<Integer> getFreeBookInstancesForThisBook(int bookID) {
        Random random = new Random();
        Set<Integer> set = new TreeSet<>();
        int bound = random.nextInt(15);
        for (int i = 0; i < bound; i++) {
            set.add(random.nextInt(27));
        }
        return new ArrayList<>(set);
    }
}

