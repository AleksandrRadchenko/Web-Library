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
}