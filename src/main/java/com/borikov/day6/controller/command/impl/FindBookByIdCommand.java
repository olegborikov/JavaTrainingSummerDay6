package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.KeyTypeData;
import com.borikov.day6.controller.command.impl.constant.KeyTypeResponse;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.BookService;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.*;

public class FindBookByIdCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookService bookService = new BookServiceImpl();
        List<Book> filteredBook = new ArrayList<>();
        if (data != null) {
            String id = data.get(KeyTypeData.ID);
            filteredBook = bookService.findBookById(id);
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(KeyTypeResponse.FILTERED_BOOK, filteredBook);
        return response;
    }
}
