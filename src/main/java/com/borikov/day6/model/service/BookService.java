package com.borikov.day6.model.service;

import com.borikov.day6.model.dao.impl.BookListDaoImpl;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.DaoException;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {
    public List<Book> addBook(Book book) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> addedBook;
        if (book == null ||
                !bookValidator.isIdCorrect(book.getBookId()) ||
                !bookValidator.isNameCorrect(book.getName()) ||
                !bookValidator.isPriceCorrect(book.getPrice()) ||
                !bookValidator.isPublishingHouseCorrect(book.getPublishingHouse()) ||
                !bookValidator.isAuthorsCorrect(book.getAuthors())) {
            addedBook = new ArrayList<>();
        } else {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                bookListDao.add(book);
                addedBook = new ArrayList<>();
                addedBook.add(book);
            } catch (DaoException e) {
                throw new ServiceException("Adding book error", e);
            }
        }
        return addedBook;
    }

    public List<Book> removeBook(Book book) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> removedBook;
        if (book == null ||
                !bookValidator.isIdCorrect(book.getBookId()) ||
                !bookValidator.isNameCorrect(book.getName()) ||
                !bookValidator.isPriceCorrect(book.getPrice()) ||
                !bookValidator.isPublishingHouseCorrect(book.getPublishingHouse()) ||
                !bookValidator.isAuthorsCorrect(book.getAuthors())) {
            removedBook = new ArrayList<>();
        } else {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                bookListDao.remove(book);
                removedBook = new ArrayList<>();
                removedBook.add(book);
            } catch (DaoException e) {
                throw new ServiceException("Removing book error", e);
            }
        }
        return removedBook;
    }

    public List<Book> findAllBooks() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> books = bookListDao.findAll();
            return books;
        } catch (DaoException e) {
            throw new ServiceException("Finding all books error", e);
        }
    }

    public Optional<Book> findBookById(long id)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        Optional<Book> currentBook;
        if (!bookValidator.isIdCorrect(id)) {
            currentBook = Optional.empty();
        } else {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                currentBook = bookListDao.findById(id);
            } catch (DaoException e) {
                throw new ServiceException("Finding book by id error", e);
            }
        }
        return currentBook;
    }

    public List<Book> findBooksByName(String name)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks;
        if (!bookValidator.isNameCorrect(name)) {
            filteredBooks = new ArrayList<>();
        } else {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByName(name);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by name error", e);
            }
        }
        return filteredBooks;
    }

    public List<Book> findBooksByPrice(Double price)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks;
        if (!bookValidator.isPriceCorrect(price)) {
            filteredBooks = new ArrayList<>();
        } else {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByPrice(price);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by price error", e);
            }
        }
        return filteredBooks;
    }

    public List<Book> findBooksByPublishingHouse(String publishingHouse)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks;
        if (!bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            filteredBooks = new ArrayList<>();
        } else {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByPublishingHouse(publishingHouse);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by publishing house error", e);
            }
        }
        return filteredBooks;
    }

    public List<Book> findBooksByAuthor(String author)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks;
        if (!bookValidator.isAuthorCorrect(author)) {
            filteredBooks = new ArrayList<>();
        } else {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByAuthor(author);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by author error", e);
            }
        }
        return filteredBooks;
    }

    public List<Book> sortBooksById() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortById();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by id error", e);
        }
    }

    public List<Book> sortBooksByName() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortByName();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by name error", e);
        }
    }

    public List<Book> sortBooksByPrice() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortByPrice();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by price error", e);
        }
    }

    public List<Book> sortBooksByPublishingHouse() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortByPublishingHouse();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by publishing house error", e);
        }
    }

    public List<Book> sortBooksByAuthors() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortByAuthors();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by authors error", e);
        }
    }
}
