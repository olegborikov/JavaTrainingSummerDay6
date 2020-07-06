package com.borikov.day6.dao.impl;

import com.borikov.day6.dao.BookListDao;
import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.IncorrectDataException;
import com.borikov.day6.repo.BookRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {

    @Override
    public void addBook(Book book) throws IncorrectDataException {
        if (book == null) {
            throw new IncorrectDataException();
        }
        BookRepo bookRepo = BookRepo.getInstance();
        bookRepo.add(book);
    }

    @Override
    public void removeBook(Book book) throws IncorrectDataException {
        if (book == null) {
            throw new IncorrectDataException();
        }
        BookRepo bookRepo = BookRepo.getInstance();
        bookRepo.remove(book);
    }

    @Override
    public List<Book> findByTag() throws IncorrectDataException {
        BookRepo bookRepo = BookRepo.getInstance();
        List<Book> books = bookRepo.get();
        return books;
    }

    @Override
    public List<Book> sortBooksByTag() throws IncorrectDataException {
        BookRepo bookRepo = BookRepo.getInstance();
        List<Book> books = bookRepo.get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(Book::getName))
                .collect(Collectors.toList());
        return sortedBooks;
    }
}
