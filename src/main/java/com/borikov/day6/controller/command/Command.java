package com.borikov.day6.controller.command;

import com.borikov.day6.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface Command {
    Map<String, List<Book>> execute(Map<String, String> data);
}
