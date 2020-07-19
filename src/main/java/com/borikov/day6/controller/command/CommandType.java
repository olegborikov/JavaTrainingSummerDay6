package com.borikov.day6.controller.command;

import com.borikov.day6.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK(new AddBookCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    FIND_ALL_BOOKS(new FindAllBooksCommand()),
    FIND_BOOK_BY_ID(new FindBookByIdCommand()),
    FIND_BOOKS_BY_NAME(new FindBooksByNameCommand()),
    FIND_BOOKS_BY_PUBLISHING_YEAR(new FindBooksByPublishingYearCommand()),
    FIND_BOOKS_BY_PUBLISHING_HOUSE(new FindBooksByPublishingHouseCommand()),
    FIND_BOOKS_BY_AUTHOR(new FindBooksByAuthorCommand()),
    SORT_BOOKS_BY_ID(new SortBooksByIdCommand()),
    SORT_BOOKS_BY_NAME(new SortBooksByNameCommand()),
    SORT_BOOKS_BY_PUBLISHING_YEAR(new SortBooksByPublishingYearCommand()),
    SORT_BOOKS_BY_PUBLISHING_HOUSE(new SortBooksByPublishingHouseCommand()),
    SORT_BOOKS_BY_AUTHORS(new SortBooksByAuthorsCommand());
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
