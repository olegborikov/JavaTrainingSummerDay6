package com.borikov.day6.dao.impl;

import com.borikov.day6.dao.BookDao;
import com.borikov.day6.entity.Book;
import com.borikov.day6.entity.Library;
import com.borikov.day6.exception.DaoException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {
    @Override
    public void addBook(Book book) throws DaoException {
        if (isBookAdded(book)) {
            throw new DaoException("Book already in library");
        }
        Library.getInstance().add(book);
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        if (!Library.getInstance().remove(book)) {
            throw new DaoException("No such book in library");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = Library.getInstance().get();
        return books;
    }

    @Override
    public Optional<Book> findBookById(long id) {
        List<Book> books = Library.getInstance().get();
        Optional<Book> filteredBooks = books.stream()
                .filter(book -> book.getBookId() == id)
                .findFirst();
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByName(String name) {
        List<Book> books = Library.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(book -> book.getName().equals(name))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPrice(Double price) {
        List<Book> books = Library.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(book -> book.getPrice() == price)
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByPublishingHouse(String publishingHouse) {
        List<Book> books = Library.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(book -> book.getPublishingHouse().equals(publishingHouse))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        List<Book> books = Library.getInstance().get();
        List<Book> filteredBooks = books.stream()
                .filter(book -> book.getAuthors().stream()
                        .anyMatch(currentAuthor -> currentAuthor.equals(author)))
                .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public List<Book> sortBooksById() {
        List<Book> books = Library.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(book -> book.getBookId()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByName() {
        List<Book> books = Library.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(book -> book.getName()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByPrice() {
        List<Book> books = Library.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(book -> book.getPrice()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByPublishingHouse() {
        List<Book> books = Library.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(book -> book.getPublishingHouse()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    @Override
    public List<Book> sortBooksByAuthors() {
        List<Book> books = Library.getInstance().get();
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(book -> book.getAuthors().size()))
                .collect(Collectors.toList());
        return sortedBooks;
    }

    private boolean isBookAdded(Book book) {
        boolean result = Library.getInstance().get().stream()
                .anyMatch(currentBook -> currentBook.equals(book));
        return result;
    }
}
