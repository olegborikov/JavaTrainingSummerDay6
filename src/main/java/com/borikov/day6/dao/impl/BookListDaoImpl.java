package com.borikov.day6.dao.impl;

import com.borikov.day6.dao.BookListDao;
import com.borikov.day6.entity.Book;
import com.borikov.day6.entity.Library;
import com.borikov.day6.exception.IncorrectDataException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {
    @Override
    public void addBook(Book book) throws IncorrectDataException {
        Library.getInstance().add(book);
    }

    @Override
    public void removeBook(Book book) throws IncorrectDataException {
        Library.getInstance().remove(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        List<Book> books = Library.getInstance().get();
        Optional<Book> filterBook = books.stream()
                .filter(book -> book.getBookId() == id)
                .findFirst();
        return filterBook;
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = Library.getInstance().get();
        List<Book> filterBooks = books.stream()
                .filter(book -> book.getName().equals(name))
                .collect(Collectors.toList());
        return filterBooks;
    }

    @Override
    public List<Book> findByPrice(Double price) {
        List<Book> books = Library.getInstance().get();
        List<Book> filterBooks = books.stream()
                .filter(book -> book.getPrice() == price)
                .collect(Collectors.toList());
        return filterBooks;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        List<Book> books = Library.getInstance().get();
        List<Book> filterBooks = books.stream()
                .filter(book -> book.getPublishingHouse().equals(publishingHouse))
                .collect(Collectors.toList());
        return filterBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = Library.getInstance().get();
        List<Book> filterBooks = books.stream()
                .filter(book -> book.getAuthors().stream()
                        .anyMatch(currentAuthor -> currentAuthor.equals(author)))
                .collect(Collectors.toList());
        return filterBooks;
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
}
