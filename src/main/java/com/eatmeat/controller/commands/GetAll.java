package com.eatmeat.controller.commands;

import com.eatmeat.controller.Command;
import com.eatmeat.service.impl.MenuReader;
import com.eatmeat.veiw.BeanViewer;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class GetAll implements Command {

    @Override
    public String execute(String request) {
        MenuReader reader = new MenuReader();
        BeanViewer viewer = new BeanViewer();
        return viewer.menuView(reader.readMenu());
    }
}
