package com.epam.wl.dao;

public class BookDAO {
    public boolean isExists() {
        // TODO look through the book table and return true if the requested book is in library
        return false;
    }

    public boolean isInstanceExists() {
        // TODO look through the book table and if the requested book have at least one free instance return true
        return false;
    }

    public int getInstanceId() {
        // TODO look through the book table and if the requested book have at least one free instance return it's id
        return -1;
    }

    // new books coming in library
    public boolean addNewBook(int id, String title, String author, int year) {
        // TODO add new book in table book_instance and in table book if necessary (with creating unique book_instanceid)
        return true;
    }

    // it is necessary then user doesn't give back the book
    public boolean removeBook(int id, String title, String author, int year) {
        // TODO remove book from book_instance and from book table if necessary (if there are no one instance anymore)
        return true;
    }

    // free book instance (user gives the book back)
    public boolean returnBook(int bookInstanceId) {
        // TODO change status from user_id to -1 (for example)
        return true;
    }

    // book the book instance (user takes)
    public boolean takeBook(int bookInstanceId) {
        // TODO change status from -1 (for example) to user_id
        return true;
    }
}