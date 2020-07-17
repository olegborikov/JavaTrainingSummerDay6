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

    public List<Book> get() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book book) throws StorageException {
        if (books.size() + 1 > MAX_CAPACITY) {
            throw new StorageException("Book storage is overflowed");
        }
        if (isBooksContains(book)) {
            throw new StorageException("Book already in storage");
        }
        books.add(book);
    }

    public void remove(Book book) throws StorageException {
        if (!isBooksContains(book)) {
            throw new StorageException("No such book in storage");
        }
        books.removeIf(currentBook -> currentBook.equalsToBook(book));
    }

    public void reset() {
        books = new ArrayList<>();
    }

    private boolean isBooksContains(Book book) {
        boolean result = false;
        for (Book currentBook : books) {
            if (currentBook.equalsToBook(book)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
