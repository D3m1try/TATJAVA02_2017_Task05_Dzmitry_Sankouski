package com.eatmeat.dao;


import com.eatmeat.bean.Dish;
import com.eatmeat.bean.Menu;
import com.eatmeat.dao.impl.DOMDAO;
import com.eatmeat.dao.impl.SAXDAO;
import com.eatmeat.dao.impl.StAXDAO;

/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
//TODO config for tuning parcers types

    private GenericDAO<Menu, String> dom = new DOMDAO();
    private GenericDAO<Menu, String> stax = new SAXDAO();
    private GenericDAO<Menu, String> sax = new StAXDAO();


    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public GenericDAO<Menu, String> getDAO(){
        return dom;
    }

}
