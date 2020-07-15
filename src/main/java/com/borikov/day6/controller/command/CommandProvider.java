package com.borikov.day6.controller.command;

public class CommandProvider {
    private static final CommandType DEFAULT_COMMAND = CommandType.FIND_ALL_BOOKS;

    public Command defineCommand(String request) {
        CommandType currentType;
        if (request == null) {
            currentType = DEFAULT_COMMAND;
        } else {
            try {
                currentType = CommandType.valueOf(request.toUpperCase());
            } catch (IllegalArgumentException e) {
                currentType = DEFAULT_COMMAND;
            }
        }
        Command currentCommand = currentType.getCommand();
        return currentCommand;
    }
}
