package com.borikov.day6.controller;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.CommandProvider;
import com.borikov.day6.entity.Book;

import java.util.List;
import java.util.Map;

public class BookController {
    public Map<String, List<Book>> processRequest(String request, Map<String, String> data) {
        Command command = CommandProvider.defineCommand(request);
        Map<String, List<Book>> response = command.execute(data);
        return response;
    }
}
