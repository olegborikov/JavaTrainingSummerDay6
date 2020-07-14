package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksByPriceCommand implements Command {
    private static final String PRICE = "price";
    private static final String FILTERED_BOOK = "filteredBooks";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookService();
        List<Book> filteredBooks;
        if (data == null) {
            filteredBooks = new ArrayList<>();
        } else {
            try {
                double price;
                if (data.get(PRICE) == null) {
                    price = -1;
                } else {
                    price = Double.parseDouble(data.get(PRICE));
                }
                filteredBooks = bookService.findBooksByPriceInLibrary(price);
            } catch (ServiceException | NumberFormatException e) {
                filteredBooks = new ArrayList<>();
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(FILTERED_BOOK, filteredBooks);
        return response;
    }
}
