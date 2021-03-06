package com.eatmeat.dao;

import com.eatmeat.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public interface Findable<T> {

    List<T> find(String fieldName, String value, boolean isPureSearch) throws DAOException;
}
