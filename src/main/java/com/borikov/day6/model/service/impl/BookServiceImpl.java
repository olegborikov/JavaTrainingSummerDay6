package com.borikov.day6.model.service.impl;

import com.borikov.day6.exception.DaoException;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.dao.impl.BookListDaoImpl;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> addBook(Book book) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> addedBook = new ArrayList<>();
        if (!(book == null ||
                !bookValidator.isIdCorrect(book.getBookId()) ||
                !bookValidator.isNameCorrect(book.getName()) ||
                !bookValidator.isPriceCorrect(book.getPrice()) ||
                !bookValidator.isPublishingHouseCorrect(book.getPublishingHouse()) ||
                !bookValidator.isAuthorsCorrect(book.getAuthors()))) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                bookListDao.add(book);
                addedBook.add(book);
            } catch (DaoException e) {
                throw new ServiceException("Adding book error", e);
            }
        }
        return addedBook;
    }

    @Override
    public List<Book> removeBook(Book book) throws ServiceException {
        List<Book> removedBook = new ArrayList<>();
        if (book != null) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                bookListDao.remove(book);
                removedBook.add(book);
            } catch (DaoException e) {
                throw new ServiceException("Removing book error", e);
            }
        }
        return removedBook;
    }

    @Override
    public List<Book> findAllBooks() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> books = bookListDao.findAll();
            return books;
        } catch (DaoException e) {
            throw new ServiceException("Finding all books error", e);
        }
    }

    @Override
    public List<Book> findBookById(long id)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBook = new ArrayList<>();
        if (bookValidator.isIdCorrect(id)) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                Optional<Book> currentBook = bookListDao.findById(id);
                currentBook.ifPresent(b -> filteredBook.add(b));
            } catch (DaoException e) {
                throw new ServiceException("Finding book by id error", e);
            }
        }
        return filteredBook;
    }

    @Override
    public List<Book> findBooksByName(String name)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isNameCorrect(name)) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByName(name);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by name error", e);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPrice(Double price)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isPriceCorrect(price)) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByPrice(price);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by price error", e);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPublishingHouse(String publishingHouse)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByPublishingHouse(publishingHouse);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by " +
                        "publishing house error", e);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByAuthor(String author)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        List<Book> filteredBooks = new ArrayList<>();
        if (bookValidator.isAuthorCorrect(author)) {
            try {
                BookListDaoImpl bookListDao = new BookListDaoImpl();
                filteredBooks = bookListDao.findByAuthor(author);
            } catch (DaoException e) {
                throw new ServiceException("Finding books by author error", e);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<Book> sortBooksById() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortById();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by id error", e);
        }
    }

    @Override
    public List<Book> sortBooksByName() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortByName();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by name error", e);
        }
    }

    @Override
    public List<Book> sortBooksByPrice() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortByPrice();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by price error", e);
        }
    }

    @Override
    public List<Book> sortBooksByPublishingHouse() throws ServiceException {
        try {
            BookListDaoImpl bookListDao = new BookListDaoImpl();
            List<Book> sortedBooks = bookListDao.sortByPublishingHouse();
            return sortedBooks;
        } catch (DaoException e) {
            throw new ServiceException("Sorting books by publishing house error", e);
        }
    }

    @Override
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
