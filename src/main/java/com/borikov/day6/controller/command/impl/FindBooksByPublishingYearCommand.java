package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.KeyTypeData;
import com.borikov.day6.controller.command.impl.constant.KeyTypeResponse;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksByPublishingYearCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookServiceImpl();
        List<Book> filteredBooks = new ArrayList<>();
        if (data != null) {
            String publishingYear = KeyTypeData.PUBLISHING_YEAR;
            filteredBooks = bookService.findBooksByPublishingYear(publishingYear);
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyTypeResponse.FILTERED_BOOK, filteredBooks);
        return response;
    }
}