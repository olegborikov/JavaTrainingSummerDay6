package com.borikov.day6.controller.command;

import com.borikov.day6.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK {{
        this.command = new AddBookCommand();
    }},
    REMOVE_BOOK {{
        this.command = new RemoveBookCommand();
    }},
    FIND_ALL_BOOKS {{
        this.command = new FindAllBooksCommand();
    }},
    FIND_BOOK_BY_ID {{
        this.command = new FindBookByIdCommand();
    }},
    FIND_BOOKS_BY_NAME {{
        this.command = new FindBooksByNameCommand();
    }},
    FIND_BOOKS_BY_PRICE {{
        this.command = new FindBooksByPriceCommand();
    }},
    FIND_BOOKS_BY_PUBLISHING_HOUSE {{
        this.command = new FindBooksByPublishingHouseCommand();
    }},
    FIND_BOOKS_BY_AUTHOR {{
        this.command = new FindBooksByAuthorCommand();
    }},
    SORT_BOOKS_BY_ID {{
        this.command = new SortBooksByIdCommand();
    }},
    SORT_BOOKS_BY_NAME {{
        this.command = new SortBooksByNameCommand();
    }},
    SORT_BOOKS_BY_PRICE {{
        this.command = new SortBooksByPriceCommand();
    }},
    SORT_BOOKS_BY_PUBLISHING_HOUSE {{
        this.command = new SortBooksByPublishingHouseCommand();
    }},
    SORT_BOOKS_BY_AUTHORS {{
        this.command = new SortBooksByAuthorsCommand();
    }};
    Command command;

    public Command getCommand() {
        return command;
    }
}
