package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String PUBLISHING_HOUSE = "publishingHouse";
    private static final String AUTHOR = "author";
    private int authorNumber = 1;
    private static final String ADDED_BOOK = "addedBook";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookService();
        List<Book> addedBook;
        if (data == null) {
            addedBook = new ArrayList<>();
        } else {
            try {
                String name = data.get(NAME);
                double price;
                if (data.get(PRICE) == null) {
                    price = -1;
                } else {
                    price = Double.parseDouble(data.get(PRICE));
                }
                String publishingHouse = data.get(PUBLISHING_HOUSE);
                List<String> authors = new ArrayList<>();
                while (data.get(AUTHOR + authorNumber) != null) {
                    authors.add(data.get(AUTHOR + authorNumber));
                    authorNumber++;
                }
                Book newBook = new Book(name, price, publishingHouse, authors);
                bookService.addBook(newBook);
                addedBook = new ArrayList<>();
                addedBook.add(newBook);
            } catch (ServiceException | NumberFormatException e) {
                addedBook = new ArrayList<>();
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ADDED_BOOK, addedBook);
        return response;
    }
}
