package com.borikov.day6.dao;

import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.IncorrectDataException;

import java.util.List;
import java.util.Optional;

public interface LibraryDao {
    void addBook(Book book) throws IncorrectDataException;

    void removeBook(Book book) throws IncorrectDataException;

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
