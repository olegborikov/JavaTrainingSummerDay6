package com.borikov.day6.service;

import com.borikov.day6.dao.impl.LibraryDaoImpl;
import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.IncorrectDataException;
import com.borikov.day6.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class LibraryService {
    public void addBookInLibrary(Book book) throws IncorrectDataException {
        BookValidator bookValidator = new BookValidator();
        if (book == null ||
                !bookValidator.isIdCorrect(book.getBookId()) ||
                !bookValidator.isNameCorrect(book.getName()) ||
                !bookValidator.isPriceCorrect(book.getPrice()) ||
                !bookValidator.isPublishingHouseCorrect(book.getPublishingHouse()) ||
                !bookValidator.isAuthorsCorrect(book.getAuthors())) {
            throw new IncorrectDataException();
        }
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        libraryDao.addBook(book);
    }

    public void removeBookFromLibrary(Book book) throws IncorrectDataException {
        if (book == null) {
            throw new IncorrectDataException();
        }
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        libraryDao.removeBook(book);
    }

    public Optional<Book> findByIdInLibrary(long id) throws IncorrectDataException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdCorrect(id)) {
            throw new IncorrectDataException();
        }
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        Optional<Book> filteredBooks = libraryDao.findBooksById(id);
        return filteredBooks;
    }

    public List<Book> findByNameInLibrary(String name) throws IncorrectDataException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isNameCorrect(name)) {
            throw new IncorrectDataException();
        }
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByName(name);
        return filteredBooks;
    }

    public List<Book> findByPriceInLibrary(Double price) throws IncorrectDataException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPriceCorrect(price)) {
            throw new IncorrectDataException();
        }
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByPrice(price);
        return filteredBooks;
    }

    public List<Book> findByPublishingHouseInLibrary(String publishingHouse) throws IncorrectDataException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isPublishingHouseCorrect(publishingHouse)) {
            throw new IncorrectDataException();
        }
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByPublishingHouse(publishingHouse);
        return filteredBooks;
    }

    public List<Book> findByAuthorInLibrary(String author) throws IncorrectDataException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isAuthorCorrect(author)) {
            throw new IncorrectDataException();
        }
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> filteredBooks = libraryDao.findBooksByAuthor(author);
        return filteredBooks;
    }

    public List<Book> sortBooksByIdInLibrary() {
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksById();
        return sortedBooks;
    }

    public List<Book> sortBooksByNameInLibrary() {
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByName();
        return sortedBooks;
    }

    public List<Book> sortBooksByPriceInLibrary() {
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByPrice();
        return sortedBooks;
    }

    public List<Book> sortBooksByPublishingHouseInLibrary() {
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByPublishingHouse();
        return sortedBooks;
    }

    public List<Book> sortBooksByAuthorsInLibrary() {
        LibraryDaoImpl libraryDao = new LibraryDaoImpl();
        List<Book> sortedBooks = libraryDao.sortBooksByAuthors();
        return sortedBooks;
    }
}
