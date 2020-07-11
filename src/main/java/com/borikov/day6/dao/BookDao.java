package com.borikov.day6.dao;

import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void addBook(Book book) throws DaoException;

    void removeBook(Book book) throws DaoException;

    List<Book> findAllBooks();

    Optional<Book> findBookById(long id);

    List<Book> findBooksByName(String name);

    List<Book> findBooksByPrice(Double price);

    List<Book> findBooksByPublishingHouse(String publishingHouse);

    List<Book> findBooksByAuthor(String author);

    List<Book> sortBooksById();

    List<Book> sortBooksByName();

    List<Book> sortBooksByPrice();

    List<Book> sortBooksByPublishingHouse();

    List<Book> sortBooksByAuthors();
}
