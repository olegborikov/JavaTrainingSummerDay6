package com.borikov.day6.service;

import com.borikov.day6.dao.impl.BookDaoImpl;
import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.DaoException;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class BookService {
    private BookDaoImpl bookDao;

    public void addBookInLibrary(Book book) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (book == null ||
                !bookValidator.isIdCorrect(book.getBookId()) ||
                !bookValidator.isNameCorrect(book.getName()) ||
                !bookValidator.isPriceCorrect(book.getPrice()) ||
                !bookValidator.isPublishingHouseCorrect(book.getPublishingHouse()) ||
                !bookValidator.isAuthorsCorrect(book.getAuthors())) {
            throw new ServiceException("Book data is incorrect");
        }
        BookDaoImpl bookDao = new BookDaoImpl();
        try {
            bookDao.addBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Adding book error", e);
        }
    }

    public void removeBookFromLibrary(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException("Book data is incorrect");
        }
        BookDaoImpl bookDao = new BookDaoImpl();
        try {
            bookDao.removeBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Removing book error", e);
        }
    }

    public List<Book> findAllBooksInLibrary() {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> books = bookDao.findAllBooks();
        return books;
    }

    public Book findBookByIdInLibrary(long id)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdCorrect(id)) {
            throw new ServiceException("Book id is incorrect");
        }
        BookDaoImpl bookDao = new BookDaoImpl();
        Optional<Book> filteredBook = bookDao.findBookById(id);
        if (filteredBook.isEmpty()) {
            throw new ServiceException("no such element");
        }
        return filteredBook.get();
    }

    public List<Book> findBooksByNameInLibrary(String name)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isNameCorrect(name)) {
            throw new ServiceException("Book name is incorrect");
        }
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> filteredBooks = bookDao.findBooksByName(name);
        return filteredBooks;
    }

    public List<Book> findBooksByPriceInLibrary(Double price)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPriceCorrect(price)) {
            throw new ServiceException("Book price is incorrect");
        }
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> filteredBooks = bookDao.findBooksByPrice(price);
        return filteredBooks;
    }

    public List<Book> findBooksByPublishingHouseInLibrary(String publishingHouse)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            throw new ServiceException("Book publishing house is incorrect");
        }
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> filteredBooks = bookDao.findBooksByPublishingHouse(publishingHouse);
        return filteredBooks;
    }

    public List<Book> findBooksByAuthorInLibrary(String author)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isAuthorCorrect(author)) {
            throw new ServiceException("Book author is incorrect");
        }
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> filteredBooks = bookDao.findBooksByAuthor(author);
        return filteredBooks;
    }

    public List<Book> sortBooksByIdInLibrary() {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> sortedBooks = bookDao.sortBooksById();
        return sortedBooks;
    }

    public List<Book> sortBooksByNameInLibrary() {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> sortedBooks = bookDao.sortBooksByName();
        return sortedBooks;
    }

    public List<Book> sortBooksByPriceInLibrary() {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> sortedBooks = bookDao.sortBooksByPrice();
        return sortedBooks;
    }

    public List<Book> sortBooksByPublishingHouseInLibrary() {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> sortedBooks = bookDao.sortBooksByPublishingHouse();
        return sortedBooks;
    }

    public List<Book> sortBooksByAuthorsInLibrary() {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> sortedBooks = bookDao.sortBooksByAuthors();
        return sortedBooks;
    }
}
