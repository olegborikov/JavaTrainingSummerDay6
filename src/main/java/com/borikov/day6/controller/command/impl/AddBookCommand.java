package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.KeyTypeData;
import com.borikov.day6.controller.command.impl.constant.KeyTypeResponse;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookServiceImpl();
        List<Book> addedBook = new ArrayList<>();
        if (data != null) {
            try {
                String name = data.get(KeyTypeData.NAME);
                String publishingYear = data.get(KeyTypeData.PUBLISHING_YEAR);
                String publishingHouse = data.get(KeyTypeData.PUBLISHING_HOUSE);
                List<String> authors = new ArrayList<>();
                int authorNumber = 1;
                while (data.get(KeyTypeData.AUTHOR + authorNumber) != null) {
                    authors.add(data.get(KeyTypeData.AUTHOR + authorNumber));
                    authorNumber++;
                }
                addedBook = bookService.addBook(name, publishingYear, publishingHouse, authors);
            } catch (ServiceException | NumberFormatException e) {
                e.printStackTrace();// TODO: 16.07.2020 log or command exception?
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyTypeResponse.ADDED_BOOK, addedBook);
        return response;
    }
}
