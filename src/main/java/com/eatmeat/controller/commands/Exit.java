package com.eatmeat.controller.commands;

import com.eatmeat.controller.Command;
import com.eatmeat.controller.exception.ControllerException;
import com.eatmeat.controller.exception.ExitException;

/**
 * Created by Dzmitry_Sankouski on 05-Feb-17.
 */
public class Exit implements Command {

    @Override
    public String execute(String request) throws ControllerException {
        throw new ExitException();
    }
}
