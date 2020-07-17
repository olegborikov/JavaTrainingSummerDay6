package com.borikov.day6.model.dao.impl;

import com.borikov.day6.exception.StorageException;
import com.borikov.day6.model.dao.BookListDao;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.entity.BookStorage;
import com.borikov.day6.exception.DaoException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {
    @Override
    public void add(Book book) throws DaoException {
        try {
            BookStorage storage = BookStorage.getInstance();
            storage.add(book);
        } catch (StorageException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Book book) throws DaoException {
        try {
            BookStorage storage = BookStorage.getInstance();
            storage.remove(book);
        } catch (StorageException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findAll() {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        return books;
    }

    @Override
    public Optional<Book> findById(long id) {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        Optional<Book> currentBook = books.stream()
                .filter(b -> b.getBookId() == id)
                .findFirst();
        return currentBook;
    }

    @Override
    public List<Book> findByName(String name) {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getName().equals(name))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findByPublishingYear(int publishingYear) {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getPublishingYear() == publishingYear)
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getPublishingHouse().equals(publishingHouse))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getAuthors().stream()
                        .anyMatch(a -> a.equals(author)))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> sortById() {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparingLong(b -> b.getBookId()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByName() {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(b -> b.getName()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByPublishingYear() {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparingInt(b -> b.getPublishingYear()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByPublishingHouse() {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(b -> b.getPublishingHouse()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByAuthors() {
        BookStorage storage = BookStorage.getInstance();
        List<Book> books = storage.get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparingInt(b -> b.getAuthors().size()))
                .collect(Collectors.toList());
        return sortedBooks;
    }
}
