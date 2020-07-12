package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.entity.Book;
import com.borikov.day6.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllBooksCommand implements Command {
    private static final String ALL_BOOKS = "allBooks";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookService();
        List<Book> books = bookService.findAllBooksInLibrary();
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ALL_BOOKS, books);
        return response;
    }
}
