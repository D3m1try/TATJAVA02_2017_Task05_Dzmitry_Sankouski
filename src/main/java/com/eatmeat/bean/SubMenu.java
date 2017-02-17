package com.eatmeat.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class SubMenu implements Serializable {
    String name;
    List<Dish> dishes = new ArrayList<Dish>();

    public SubMenu(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
