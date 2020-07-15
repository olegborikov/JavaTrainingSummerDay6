package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.KeyType;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksByAuthorCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> filteredBooks= new ArrayList<>();
        if (data != null) {
            try {
                String author = data.get(KeyType.AUTHOR);
                filteredBooks = bookService.findBooksByAuthor(author);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyType.FILTERED_BOOKS, filteredBooks);
        return response;
    }
} 
