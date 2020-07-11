package com.borikov.day6.factory;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.CommandType;
import com.borikov.day6.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<CommandType, Command> commands;

    public CommandFactory() {
        this.commands = new HashMap<>();
        commands.put(CommandType.ADD_BOOK, new AddBookCommand());
        commands.put(CommandType.REMOVE_BOOK, new RemoveBookCommand());
        commands.put(CommandType.FIND_ALL_BOOKS, new FindAllBooksCommand());
        commands.put(CommandType.FIND_BOOK_BY_ID, new FindBookByIdCommand());
        commands.put(CommandType.FIND_BOOKS_BY_NAME, new FindBooksByNameCommand());
        commands.put(CommandType.FIND_BOOKS_BY_PRICE, new FindBooksByPriceCommand());
        commands.put(CommandType.FIND_BOOKS_BY_PUBLISHING_HOUSE, new FindBooksByPublishingHouseCommand());
        commands.put(CommandType.FIND_BOOKS_BY_AUTHOR, new FindBooksByAuthorCommand());
        commands.put(CommandType.SORT_BOOKS_BY_ID, new SortBooksByIdCommand());
        commands.put(CommandType.SORT_BOOKS_BY_NAME, new SortBooksByNameCommand());
        commands.put(CommandType.SORT_BOOKS_BY_PRICE, new SortBooksByPriceCommand());
        commands.put(CommandType.SORT_BOOKS_BY_PUBLISHING_HOUSE, new SortBooksByPublishingHouseCommand());
        commands.put(CommandType.SORT_BOOKS_BY_AUTHORS, new SortBooksByAuthorsCommand());
    }

    public Command defineCommand(CommandType commandType) {
        Command command = commands.get(commandType);
        return command;
    }
}
