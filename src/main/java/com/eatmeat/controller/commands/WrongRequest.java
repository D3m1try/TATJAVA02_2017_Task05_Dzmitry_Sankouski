package com.eatmeat.controller.commands;


import com.eatmeat.controller.Command;
import com.eatmeat.controller.exception.UnknownCommand;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class WrongRequest implements Command {

    @Override
    public String execute(String request) throws  UnknownCommand {
        throw new UnknownCommand();

    }
}
