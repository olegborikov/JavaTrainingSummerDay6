package com.borikov.day6.controller.command;

public class CommandProvider {
    public static Command defineCommand(String request){
        if (request == null || request.isBlank()){
         //emptyCommand
        }
        return null;
    }
}
