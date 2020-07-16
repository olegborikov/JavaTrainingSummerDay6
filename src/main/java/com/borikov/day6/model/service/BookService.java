package com.borikov.day6.model.service;

import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> addBook(String name, String publishingYear, String publishingHouse,
                       List<String> authors) throws ServiceException;

    List<Book> removeBook(String name, String publishingYear,
                          String publishingHouse,
                          List<String> authors) throws ServiceException;

    List<Book> findAllBooks();

    List<Book> findBookById(String id);

    List<Book> findBooksByName(String name);

    List<Book> findBooksByPublishingYear(String publishingYear);

    List<Book> findBooksByPublishingHouse(String publishingHouse);

    List<Book> findBooksByAuthor(String author);

    List<Book> sortBooksById();

    List<Book> sortBooksByName();

    List<Book> sortBooksByPublishingYear();

    List<Book> sortBooksByPublishingHouse();

    List<Book> sortBooksByAuthors();
}
