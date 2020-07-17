package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.DataKeyType;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyType;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksByPublishingHouseCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        List<Book> filteredBooks = new ArrayList<>();
        if (data != null) {
            BookServiceImpl bookService = new BookServiceImpl();
            String publishingHouse = data.get(DataKeyType.PUBLISHING_HOUSE);
            filteredBooks = bookService.findBooksByPublishingHouse(publishingHouse);
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ResponseKeyType.FILTERED_BOOKS, filteredBooks);
        return response;
    }
}
