package com.borikov.day6.controller.command.impl;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.entity.Book;

import java.util.List;
import java.util.Map;

public class SortBooksByIdCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> data) {
        return null;
    }
}
