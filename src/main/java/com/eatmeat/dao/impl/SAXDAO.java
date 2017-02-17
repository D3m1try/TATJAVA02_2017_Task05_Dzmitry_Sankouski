package com.eatmeat.dao.impl;

import com.eatmeat.bean.Dish;
import com.eatmeat.bean.Menu;
import com.eatmeat.dao.GenericDAO;
import com.eatmeat.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class SAXDAO  implements GenericDAO<Menu, String> {

    public String create(Menu newInstance) throws DAOException {
        return null;
    }

    public Menu read(String id) throws DAOException {
        return null;
    }

    public void update(Menu transientObject) throws DAOException {

    }

    public void delete(Menu persistentObject) {

    }

    public List find(String fieldName, String value, boolean isPureSearch) {
        return null;
    }
}
