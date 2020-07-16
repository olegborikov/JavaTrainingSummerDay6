package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.KeyType;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> addedBook = new ArrayList<>();
        if (!(data == null || data.get(KeyType.PRICE) == null)) {
            try {
                String name = data.get(KeyType.NAME);
                double price = Double.parseDouble(data.get(KeyType.PRICE));
                String publishingHouse = data.get(KeyType.PUBLISHING_HOUSE);
                List<String> authors = new ArrayList<>();
                int authorNumber = 1;
                while (data.get(KeyType.AUTHOR + authorNumber) != null) {
                    authors.add(data.get(KeyType.AUTHOR + authorNumber));
                    authorNumber++;
                }
                Book newBook = new Book(name, price, publishingHouse, authors);
                addedBook = bookService.addBook(newBook);
            } catch (ServiceException | NumberFormatException e) {
                e.printStackTrace();// TODO: 16.07.2020 log or command exception?
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyType.ADDED_BOOK, addedBook);
        return response;
    }
}
