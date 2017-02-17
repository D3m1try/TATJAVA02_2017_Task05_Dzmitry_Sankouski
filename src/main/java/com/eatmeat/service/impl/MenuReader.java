package com.eatmeat.service.impl;

import com.eatmeat.bean.Menu;
import com.eatmeat.dao.DAOFactory;
import com.eatmeat.dao.exception.DAOException;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class MenuReader {
    public Menu readMenu(){
        Menu menu = null;
        try {
            menu = DAOFactory.getInstance().getDAO().read("C:\\Users\\Dzmitry_Sankouski\\IdeaProjects\\TATJAVA02_2017_Task05_Dzmitry_Sankouski\\src\\main\\resources\\menu.xml");
        } catch (DAOException e) {
            e.printStackTrace();//TODO throw exception
        }
        return menu;
    }

}
