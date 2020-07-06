package com.borikov.day6.dao;

import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.IncorrectDataException;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws IncorrectDataException;

    void removeBook(Book book) throws IncorrectDataException;

    List<Book> findByTag() throws IncorrectDataException;

    List<Book> sortBooksByTag() throws IncorrectDataException;
}
