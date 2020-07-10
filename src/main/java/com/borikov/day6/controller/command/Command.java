package com.borikov.day6.controller.command;

import java.util.Map;

public interface Command {
    void execute(Map<String, String> data);
}
