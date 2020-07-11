package com.borikov.day6.controller;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.CommandType;
import com.borikov.day6.controller.command.CommandProvider;
import com.borikov.day6.factory.CommandFactory;

import java.util.Map;

public class BookController {
    public void processRequest(String request, Map<String, String> data) {
        CommandType commandType = CommandProvider.defineCommand(request);
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.defineCommand(commandType);
        command.execute(data);
    }
}
