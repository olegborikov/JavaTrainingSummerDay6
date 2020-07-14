package com.borikov.day6.model.dao;

import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    void add(Book book) throws DaoException;

    void remove(Book book) throws DaoException;

    List<Book> findAll();

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findByPrice(Double price);

    List<Book> findByPublishingHouse(String publishingHouse);

    List<Book> findByAuthor(String author);

    List<Book> sortById();

    List<Book> sortByName();

    List<Book> sortByPrice();

    List<Book> sortByPublishingHouse();

    List<Book> sortByAuthors();
}
