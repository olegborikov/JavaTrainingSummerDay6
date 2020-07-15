package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.KeyType;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.*;

public class FindBookByIdCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> filteredBook = new ArrayList<>();
        if (!(data == null || data.get(KeyType.ID) == null)) {
            try {
                long id = Long.parseLong(data.get(KeyType.ID));
                Optional<Book> currentBook = bookService.findBookById(id);
                currentBook.ifPresent(b -> filteredBook.add(b));
            } catch (ServiceException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyType.FILTERED_BOOK, filteredBook);
        return response;
    }
}
