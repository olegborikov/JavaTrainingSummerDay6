package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyType;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortBooksByPublishingHouseCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> sortedBooks = bookService.sortBooksByPublishingHouse();
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ResponseKeyType.SORTED_BOOKS, sortedBooks);
        return response;
    }
}
