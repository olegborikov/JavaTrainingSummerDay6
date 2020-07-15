package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksByPublishingHouseCommand implements Command {

    private static final String PUBLISHING_HOUSE = "publishingHouse";
    private static final String FILTERED_BOOKS = "filteredBooks";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookService();
        List<Book> filteredBooks;
        if (data == null) {
            filteredBooks = new ArrayList<>();
        } else {
            try {
                String publishingHouse = data.get(PUBLISHING_HOUSE);
                filteredBooks = bookService.findBooksByPublishingHouse(publishingHouse);
            } catch (ServiceException e) {
                filteredBooks = new ArrayList<>();
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(FILTERED_BOOKS, filteredBooks);
        return response;
    }
}
