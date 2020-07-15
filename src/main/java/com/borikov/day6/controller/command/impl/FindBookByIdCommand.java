package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBookByIdCommand implements Command {
    private static final String ID = "id";
    private static final String FILTERED_BOOK = "filteredBook";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookService();
        List<Book> filteredBook;
        if (data == null) {
            filteredBook = new ArrayList<>();
        } else {
            try {
                long id;
                if (data.get(ID) == null) {
                    id = -1;
                } else {
                    id = Long.parseLong(data.get(ID));
                }
                Book book = bookService.findBookById(id);
                filteredBook = new ArrayList<>();
                filteredBook.add(book);
            } catch (ServiceException | NumberFormatException e) {
                filteredBook = new ArrayList<>();
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(FILTERED_BOOK, filteredBook);
        return response;
    }
}
