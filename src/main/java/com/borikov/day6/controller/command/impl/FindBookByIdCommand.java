package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBookByIdCommand implements Command {
    private static final String ID = "id";
    private static final String FILTERED_BOOK = "filteredBook";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        List<Book> bookList;
        BookService bookService = new BookService();
        try {
            long id = Long.parseLong(data.get(ID));
            Book book = bookService.findBookByIdInLibrary(id);
            bookList = new ArrayList<>();
            bookList.add(book);
        } catch (ServiceException | NumberFormatException e) {
            bookList = new ArrayList<>();
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(FILTERED_BOOK, bookList);
        return response;
    }
}
