package com.borikov.day6.dao;

import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.IncorrectDataException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    void addBook(Book book) throws IncorrectDataException;

    void removeBook(Book book) throws IncorrectDataException;

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findByPrice(Double price);

    List<Book> findByPublishingHouse(String publishingHouse);

    List<Book> findByAuthor(String author);

    List<Book> sortBooksById();

    List<Book> sortBooksByName();

    List<Book> sortBooksByPrice();

    List<Book> sortBooksByPublishingHouse();

    List<Book> sortBooksByAuthors();
}
