package com.borikov.day6.controller.command;

import com.borikov.day6.controller.command.impl.AddBookCommand;
import com.borikov.day6.controller.command.impl.RemoveBookCommand;

public enum CommandType {
    ADD_BOOK {{
        this.command = new AddBookCommand();
    }},
    REMOVE_BOOK {{
        this.command = new RemoveBookCommand();
    }},
    GET_ALL_BOOKS,
    FIND_BOOK_BY_ID,
    FIND_BOOKS_BY_NAME,
    FIND_BOOKS_BY_PRICE,
    FIND_BOOKS_BY_PUBLISHING_HOUSE,
    FIND_BOOKS_BY_AUTHOR,
    SORT_BOOKS_BY_ID,
    SORT_BOOKS_BY_NAME,
    SORT_BOOKS_BY_PRICE,
    SORT_BOOKS_BY_PUBLISHING_HOUSE,
    SORT_BOOKS_BY_AUTHORS;
    Command command;

    public Command getCommand() {
        return command;
    }
}
