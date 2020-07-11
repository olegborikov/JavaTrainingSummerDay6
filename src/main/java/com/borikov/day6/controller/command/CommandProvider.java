package com.borikov.day6.controller.command;

public class CommandProvider {
    private static final CommandType DEFAULT_COMMAND = CommandType.FIND_ALL_BOOKS;

    private CommandProvider() {
    }

    public static CommandType defineCommand(String request) {
        CommandType commandType;
        if (request == null || request.isBlank()) {
            return DEFAULT_COMMAND;
        } else {
            try {
                commandType = CommandType.valueOf(request.toUpperCase());
            } catch (IllegalArgumentException e) {
                commandType = DEFAULT_COMMAND;
            }
        }
        return commandType;
    }
}
