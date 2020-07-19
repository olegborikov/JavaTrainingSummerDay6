package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.DataKeyType;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyType;
import com.borikov.day6.exception.ServiceException;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveBookCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> removedBook = new ArrayList<>();
        String responseKey = ResponseKeyType.REMOVED_BOOK;
        if (data != null) {
            try {
                String name = data.get(DataKeyType.NAME);
                String publishingYear = data.get(DataKeyType.PUBLISHING_YEAR);
                String publishingHouse = data.get(DataKeyType.PUBLISHING_HOUSE);
                List<String> authors = new ArrayList<>();
                int authorNumber = 1;
                while (data.get(DataKeyType.AUTHOR + authorNumber) != null) {
                    authors.add(data.get(DataKeyType.AUTHOR + authorNumber));
                    authorNumber++;
                }
                removedBook = bookService.removeBook(name, publishingYear,
                        publishingHouse, authors);
            } catch (ServiceException e) {
                responseKey = ResponseKeyType.ERROR;
            }
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(responseKey, removedBook);
        return response;
    }
}
