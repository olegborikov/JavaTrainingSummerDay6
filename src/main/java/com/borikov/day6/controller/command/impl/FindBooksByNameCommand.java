package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksByNameCommand implements Command {

    private static final String NAME = "name";
    private static final String FILTERED_BOOK = "filteredBooks";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookService();
        List<Book> filteredBooks;
        if (data == null) {
            filteredBooks = new ArrayList<>();
        } else {
            try {
                String name = data.get(NAME);
                filteredBooks = bookService.findBooksByNameInLibrary(name);
            } catch (ServiceException e) {
                filteredBooks = new ArrayList<>();
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(FILTERED_BOOK, filteredBooks);
        return response;
    }
}
