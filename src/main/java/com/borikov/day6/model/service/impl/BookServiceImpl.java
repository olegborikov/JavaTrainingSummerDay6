package com.borikov.day6.model.service.impl;

import com.borikov.day6.exception.DaoException;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.dao.impl.BookListDaoImpl;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.util.BookParser;
import com.borikov.day6.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> addBook(String name, String publishingYear,
                              String publishingHouse,
                              List<String> authors) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        List<Book> addedBook = new ArrayList<>();
        int publishingYearParsed = bookParser.parsePublishingYear(publishingYear);
        if (!bookValidator.isPublishingYearCorrect(publishingYearParsed) ||
                !bookValidator.isNameCorrect(name) ||
                !bookValidator.isPublishingHouseCorrect(publishingHouse) ||
                !bookValidator.isAuthorsCorrect(authors)) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                Book book = new Book(name, publishingYearParsed, publishingHouse, authors);
                bookListDao.add(book);
                addedBook.add(book);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return addedBook;
    }

    @Override
    public List<Book> removeBook(String name, String publishingYear,
                                 String publishingHouse,
                                 List<String> authors) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        List<Book> removedBook = new ArrayList<>();
        int publishingYearParsed = bookParser.parsePublishingYear(publishingYear);
        if (!bookValidator.isPublishingYearCorrect(publishingYearParsed) ||
                !bookValidator.isNameCorrect(name) ||
                !bookValidator.isPublishingHouseCorrect(publishingHouse) ||
                !bookValidator.isAuthorsCorrect(authors)) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                Book book = new Book(name, publishingYearParsed, publishingHouse, authors);
                bookListDao.remove(book);
                removedBook.add(book);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return removedBook;
    }

    @Override
    public List<Book> findAllBooks() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> books = bookListDao.findAll();
        return books;
    }

    @Override
    public List<Book> findBookById(String id) {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        List<Book> currentBook = new ArrayList<>();
        long idParsed = bookParser.parseId(id);
        if (bookValidator.isIdCorrect(idParsed)) {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            Optional<Book> book = bookListDao.findById(idParsed);
            book.ifPresent(b -> currentBook.add(b));
        }
        return currentBook;
    }

    @Override
    public List<Book> findBooksByName(String name) {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isNameCorrect(name)) {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            filteredBooks = bookListDao.findByName(name);
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPublishingYear(String publishingYear) {
        BookValidator bookValidator = new BookValidator();
        BookParser bookParser = new BookParser();
        List<Book> filteredBooks = new ArrayList<>();
        int publishingYearParsed = bookParser.parsePublishingYear(publishingYear);
        if (bookValidator.isPublishingYearCorrect(publishingYearParsed)) {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            filteredBooks = bookListDao.findByPublishingYear(publishingYearParsed);
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPublishingHouse(String publishingHouse) {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            filteredBooks = bookListDao.findByPublishingHouse(publishingHouse);

        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isAuthorCorrect(author)) {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            filteredBooks = bookListDao.findByAuthor(author);
        }
        return filteredBooks;
    }

    @Override
    public List<Book> sortBooksById() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookListDao.sortById();
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByName() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookListDao.sortByName();
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByPublishingYear() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookListDao.sortByPublishingYear();
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByPublishingHouse() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookListDao.sortByPublishingHouse();
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByAuthors() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookListDao.sortByAuthors();
        return sortedBooks;
    }
}
