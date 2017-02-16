package com.eatmeat.dao;


/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();


    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }


}
