package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortBooksByNameCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> sortedBooks = bookService.sortBooksByName();
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ResponseKeyName.SORTED_BOOKS, sortedBooks);
        return response;
    }
}
