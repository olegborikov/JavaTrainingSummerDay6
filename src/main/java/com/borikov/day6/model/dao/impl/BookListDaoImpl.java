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
            BookStorage.getInstance().add(book);
        } catch (StorageException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Book book) throws DaoException {
        try {
            BookStorage.getInstance().remove(book);
        } catch (StorageException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = BookStorage.getInstance().get();
        return books;
    }

    @Override
    public Optional<Book> findById(long id) {
        List<Book> books = BookStorage.getInstance().get();
        Optional<Book> filteredBooks = books.stream()
                .filter(b -> b.getBookId() == id)
                .findFirst();
        return filteredBooks;
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getName().equals(name))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findByPrice(Double price) {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getPrice() == price)
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getPublishingHouse().equals(publishingHouse))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(b -> b.getAuthors().stream()
                        .anyMatch(currentAuthor -> currentAuthor.equals(author)))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> sortById() {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(b -> b.getBookId()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByName() {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(b -> b.getName()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByPrice() {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(b -> b.getPrice()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByPublishingHouse() {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(b -> b.getPublishingHouse()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortByAuthors() {
        List<Book> books = BookStorage.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(b -> b.getAuthors().size()))
                .collect(Collectors.toList());
        return sortedBooks;
    }
}
