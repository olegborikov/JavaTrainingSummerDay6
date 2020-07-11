package com.borikov.day6.service;

import com.borikov.day6.dao.impl.BookDaoImpl;
import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.DaoException;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class BookService {
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
        BookDaoImpl libraryDao = new BookDaoImpl();
        try {
            libraryDao.addBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Adding book error", e);
        }
    }

    public void removeBookFromLibrary(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException("Book data is incorrect");
        }
        BookDaoImpl libraryDao = new BookDaoImpl();
        try {
            libraryDao.removeBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Removing book error", e);
        }
    }

    public List<Book> getAllBooksInLibrary() {
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> books = libraryDao.findAllBooks();
        return books;
    }

    public Book findBookByIdInLibrary(long id)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdCorrect(id)) {
            throw new ServiceException("Book id is incorrect");
        }
        BookDaoImpl libraryDao = new BookDaoImpl();
        Optional<Book> filteredBook = libraryDao.findBookById(id);
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
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByName(name);
        return filteredBooks;
    }

    public List<Book> findBooksByPriceInLibrary(Double price)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPriceCorrect(price)) {
            throw new ServiceException("Book price is incorrect");
        }
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByPrice(price);
        return filteredBooks;
    }

    public List<Book> findBooksByPublishingHouseInLibrary(String publishingHouse)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            throw new ServiceException("Book publishing house is incorrect");
        }
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByPublishingHouse(publishingHouse);
        return filteredBooks;
    }

    public List<Book> findBooksByAuthorInLibrary(String author)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isAuthorCorrect(author)) {
            throw new ServiceException("Book author is incorrect");
        }
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByAuthor(author);
        return filteredBooks;
    }

    public List<Book> sortBooksByIdInLibrary() {
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksById();
        return sortedBooks;
    }

    public List<Book> sortBooksByNameInLibrary() {
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByName();
        return sortedBooks;
    }

    public List<Book> sortBooksByPriceInLibrary() {
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByPrice();
        return sortedBooks;
    }

    public List<Book> sortBooksByPublishingHouseInLibrary() {
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByPublishingHouse();
        return sortedBooks;
    }

    public List<Book> sortBooksByAuthorsInLibrary() {
        BookDaoImpl libraryDao = new BookDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByAuthors();
        return sortedBooks;
    }
}
