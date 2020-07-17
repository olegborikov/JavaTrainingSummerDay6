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

public class FindBookByIdCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> filteredBook = new ArrayList<>();
        if (data != null) {
            String id = data.get(DataKeyType.ID);
            filteredBook = bookService.findBookById(id);
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ResponseKeyType.CURRENT_BOOK, filteredBook);
        return response;
    }
}
