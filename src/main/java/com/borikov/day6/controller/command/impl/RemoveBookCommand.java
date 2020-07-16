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

public class RemoveBookCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> removedBook = new ArrayList<>();
        if (data != null) {
            try {
                String name = data.get(KeyType.NAME);
                double price;
                if (data.get(KeyType.PRICE) == null) {
                    price = -1;
                } else {
                    price = Double.parseDouble(data.get(KeyType.PRICE));
                }
                String publishingHouse = data.get(KeyType.PUBLISHING_HOUSE);
                int authorNumber = 1;
                List<String> authors = new ArrayList<>();
                while (data.get(KeyType.AUTHOR + authorNumber) != null) {
                    authors.add(data.get(KeyType.AUTHOR + authorNumber));
                    authorNumber++;
                }
                Book book = new Book(name, price, publishingHouse, authors);
                bookService.removeBook(book);
                removedBook = new ArrayList<>();
                removedBook.add(book);
            } catch (ServiceException | NumberFormatException e) {
                e.printStackTrace();// TODO: 16.07.2020 log or command exception?
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyType.REMOVED_BOOK, removedBook);
        return response;
    }
}
