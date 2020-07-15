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
        BookStorage storage = BookStorage.getInstance();
        try {
            storage.add(book);
        } catch (StorageException e) {
            throw new DaoException("Storage error while adding book", e);
        }
    }

    @Override
    public void remove(Book book) throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            storage.remove(book);
        } catch (StorageException e) {
            throw new DaoException("Storage error while removing book", e);
        }
    }

    @Override
    public List<Book> findAll() throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            return books;
        } catch (StorageException e) {
            throw new DaoException("Storage error while finding all books", e);
        }
    }

    @Override
    public Optional<Book> findById(long id) throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            Optional<Book> currentBook = books.stream()
                    .filter(b -> b.getBookId() == id)
                    .findFirst();
            return currentBook;
        } catch (StorageException e) {
            throw new DaoException("Storage error while finding book by id", e);
        }
    }

    @Override
    public List<Book> findByName(String name) throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> filteredBooks = books.stream()
                    .filter(b -> b.getName().equals(name))
                    .collect(Collectors.toList());
            return filteredBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while finding books by name", e);
        }
    }

    @Override
    public List<Book> findByPrice(Double price) throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> filteredBooks = books.stream()
                    .filter(b -> b.getPrice() == price)
                    .collect(Collectors.toList());
            return filteredBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while finding books by price", e);
        }
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse)
            throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> filteredBooks = books.stream()
                    .filter(b -> b.getPublishingHouse().equals(publishingHouse))
                    .collect(Collectors.toList());
            return filteredBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while finding books by publishing house", e);
        }
    }

    @Override
    public List<Book> findByAuthor(String author) throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> filteredBooks = books.stream()
                    .filter(b -> b.getAuthors().stream()
                            .anyMatch(a -> a.equals(author)))
                    .collect(Collectors.toList());
            return filteredBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while finding books by author", e);
        }
    }

    @Override
    public List<Book> sortById() throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> sortedBooks = books.stream()
                    .sorted(Comparator.comparing(b -> b.getBookId()))
                    .collect(Collectors.toList());
            return sortedBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while sorting books by id", e);
        }
    }

    @Override
    public List<Book> sortByName() throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> sortedBooks = books.stream()
                    .sorted(Comparator.comparing(b -> b.getName()))
                    .collect(Collectors.toList());
            return sortedBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while sorting books by name", e);
        }
    }

    @Override
    public List<Book> sortByPrice() throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> sortedBooks = books.stream()
                    .sorted(Comparator.comparing(b -> b.getPrice()))
                    .collect(Collectors.toList());
            return sortedBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while sorting books by price", e);
        }
    }

    @Override
    public List<Book> sortByPublishingHouse() throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> sortedBooks = books.stream()
                    .sorted(Comparator.comparing(b -> b.getPublishingHouse()))
                    .collect(Collectors.toList());
            return sortedBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while sorting books by publishing house", e);
        }
    }

    @Override
    public List<Book> sortByAuthors() throws DaoException {
        BookStorage storage = BookStorage.getInstance();
        try {
            List<Book> books = storage.get();
            List<Book> sortedBooks = books.stream()
                    .sorted(Comparator.comparing(b -> b.getAuthors().size()))
                    .collect(Collectors.toList());
            return sortedBooks;
        } catch (StorageException e) {
            throw new DaoException("Storage error while sorting books by authors", e);
        }
    }
}
