package com.borikov.day6.model.service;

import com.borikov.day6.model.dao.impl.BookListDaoImpl;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.DaoException;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class BookService {
    private BookListDaoImpl bookDao;

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
        BookListDaoImpl bookDao = new BookListDaoImpl();
        try {
            bookDao.add(book);
        } catch (DaoException e) {
            throw new ServiceException("Adding book error", e);
        }
    }

    public void removeBookFromLibrary(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException("Book data is incorrect");
        }
        BookListDaoImpl bookDao = new BookListDaoImpl();
        try {
            bookDao.remove(book);
        } catch (DaoException e) {
            throw new ServiceException("Removing book error", e);
        }
    }

    public List<Book> findAllBooksInLibrary() {
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> books = bookDao.findAll();
        return books;
    }

    public Book findBookByIdInLibrary(long id)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdCorrect(id)) {
            throw new ServiceException("Book id is incorrect");
        }
        BookListDaoImpl bookDao = new BookListDaoImpl();
        Optional<Book> filteredBook = bookDao.findById(id);
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
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> filteredBooks = bookDao.findByName(name);
        return filteredBooks;
    }

    public List<Book> findBooksByPriceInLibrary(Double price)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPriceCorrect(price)) {
            throw new ServiceException("Book price is incorrect");
        }
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> filteredBooks = bookDao.findByPrice(price);
        return filteredBooks;
    }

    public List<Book> findBooksByPublishingHouseInLibrary(String publishingHouse)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            throw new ServiceException("Book publishing house is incorrect");
        }
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> filteredBooks = bookDao.findByPublishingHouse(publishingHouse);
        return filteredBooks;
    }

    public List<Book> findBooksByAuthorInLibrary(String author)
            throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isAuthorCorrect(author)) {
            throw new ServiceException("Book author is incorrect");
        }
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> filteredBooks = bookDao.findByAuthor(author);
        return filteredBooks;
    }

    public List<Book> sortBooksByIdInLibrary() {
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookDao.sortById();
        return sortedBooks;
    }

    public List<Book> sortBooksByNameInLibrary() {
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookDao.sortByName();
        return sortedBooks;
    }

    public List<Book> sortBooksByPriceInLibrary() {
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookDao.sortByPrice();
        return sortedBooks;
    }

    public List<Book> sortBooksByPublishingHouseInLibrary() {
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookDao.sortByPublishingHouse();
        return sortedBooks;
    }

    public List<Book> sortBooksByAuthorsInLibrary() {
        BookListDaoImpl bookDao = new BookListDaoImpl();
        List<Book> sortedBooks = bookDao.sortByAuthors();
        return sortedBooks;
    }
}
