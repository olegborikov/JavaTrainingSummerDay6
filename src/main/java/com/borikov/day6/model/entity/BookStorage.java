package com.borikov.day6.model.entity;

import com.borikov.day6.exception.StorageException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStorage {
    private static BookStorage instance;
    private List<Book> books;
    private static final int MAX_CAPACITY = 100;

    private BookStorage() {
        books = new ArrayList<>();
    }

    public static BookStorage getInstance() {
        if (instance == null) {
            instance = new BookStorage();
        }
        return instance;
    }

    public void set(List<Book> books) {
        this.books = books;
    }

    public List<Book> get() throws StorageException {
        if (books == null) {
            throw new StorageException("There is no book storage");
        }
        return Collections.unmodifiableList(books);
    }

    public void add(Book book) throws StorageException {
        if (books == null) {
            throw new StorageException("There is no book storage");
        }
        if (books.size() >= MAX_CAPACITY) {
            throw new StorageException("Book storage is overflowed");
        }
        if (books.contains(book)) {
            throw new StorageException("Book already in storage");
        }
        books.add(book);
    }

    public void remove(Book book) throws StorageException {
        if (books == null) {
            throw new StorageException("There is no book storage");
        }
        if (!books.contains(book)) {
            throw new StorageException("No such book in storage");
        }
        books.remove(book);
    }
}
