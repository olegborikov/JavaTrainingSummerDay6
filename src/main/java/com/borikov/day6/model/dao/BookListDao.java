package com.borikov.day6.model.dao;

import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    void add(Book book) throws DaoException;

    void remove(Book book) throws DaoException;

    List<Book> findAll() throws DaoException;

    Optional<Book> findById(long id) throws DaoException;

    List<Book> findByName(String name) throws DaoException;

    List<Book> findByPrice(Double price) throws DaoException;

    List<Book> findByPublishingHouse(String publishingHouse) throws DaoException;

    List<Book> findByAuthor(String author) throws DaoException;

    List<Book> sortById() throws DaoException;

    List<Book> sortByName() throws DaoException;

    List<Book> sortByPrice() throws DaoException;

    List<Book> sortByPublishingHouse() throws DaoException;

    List<Book> sortByAuthors() throws DaoException;
}
