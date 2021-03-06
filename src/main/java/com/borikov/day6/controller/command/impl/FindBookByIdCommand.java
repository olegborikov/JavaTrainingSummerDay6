package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.impl.constant.DataKeyName;
import com.borikov.day6.controller.command.impl.constant.ResponseKeyName;
import com.borikov.day6.model.entity.Book;
import com.borikov.day6.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBookByIdCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        List<Book> currentBook = new ArrayList<>();
        if (data != null) {
            BookServiceImpl bookService = new BookServiceImpl();
            String id = data.get(DataKeyName.ID);
            currentBook = bookService.findBookById(id);
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ResponseKeyName.CURRENT_BOOK, currentBook);
        return response;
    }
}
