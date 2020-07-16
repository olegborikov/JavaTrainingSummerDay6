package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.KeyTypeResponse;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortBooksByPublishingYearCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookServiceImpl();
        List<Book> sortedBooks = bookService.sortBooksByPublishingYear();
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyTypeResponse.SORTED_BOOKS, sortedBooks);
        return response;
    }
}