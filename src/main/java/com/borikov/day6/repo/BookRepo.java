package com.borikov.day6.repo;

import com.borikov.day6.entity.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookRepo {
    private static final BookRepo INSTANCE = new BookRepo();
    private final List<Book> books = new ArrayList<Book>();

    private BookRepo() {
    }

    public static BookRepo getInstance() {
        return INSTANCE;
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
