package com.borikov.day6.controller;

import com.borikov.day6.controller.command.Command;
import com.borikov.day6.controller.command.CommandProvider;

import java.util.Map;

public class BookController {
    public void processRequest(String request, Map<String, String> data) {
        Command command = CommandProvider.defineCommand(request);
        command.execute(data);
    }
}
