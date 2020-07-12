package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String PUBLISHING_HOUSE = "publishingHouse";
    private static final String AUTHOR = "author";
    private int authorNumber = 1;

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookService();
        try {
            String name = data.get(NAME);
            double price = Double.parseDouble(data.get(PRICE));
            String publishingHouse = data.get(PUBLISHING_HOUSE);
            List<String> authors = new ArrayList<>();
            while (data.get(AUTHOR + authorNumber) != null) {
                authors.add(data.get(AUTHOR + authorNumber));
                authorNumber++;
            }
            Book book = new Book(name, price, publishingHouse, authors);
            bookService.addBookInLibrary(book);
        } catch (ServiceException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
