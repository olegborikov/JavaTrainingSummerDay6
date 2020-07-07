package com.borikov.day6.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private static Library instance = new Library();
    private List<Book> books = new ArrayList<>();

    private Library() {
    }

    public static Library getInstance() {
        return instance;
    }

    public boolean add(Book book) {
        return books.add(book);
    }

    public List<Book> get() {
        return Collections.unmodifiableList(books);
    }

    public boolean remove(Book book) {
        return books.remove(book);
    }
}
