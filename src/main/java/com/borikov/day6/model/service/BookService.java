package com.borikov.day6.model.service;

import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> addBook(Book book) throws ServiceException;

    List<Book> removeBook(Book book) throws ServiceException;

    List<Book> findAllBooks() throws ServiceException;

    List<Book> findBookById(long id) throws ServiceException;

    List<Book> findBooksByName(String name) throws ServiceException;

    List<Book> findBooksByPrice(Double price) throws ServiceException;

    List<Book> findBooksByPublishingHouse(String publishingHouse)
            throws ServiceException;

    List<Book> findBooksByAuthor(String author) throws ServiceException;

    List<Book> sortBooksById() throws ServiceException;

    List<Book> sortBooksByName() throws ServiceException;

    List<Book> sortBooksByPrice() throws ServiceException;

    List<Book> sortBooksByPublishingHouse() throws ServiceException;

    List<Book> sortBooksByAuthors() throws ServiceException;
}
