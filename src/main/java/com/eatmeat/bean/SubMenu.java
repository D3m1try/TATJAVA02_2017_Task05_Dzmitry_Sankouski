package com.eatmeat.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class SubMenu implements Serializable {
    private String name;
    private List<Dish> dishes = new ArrayList<Dish>();

    public SubMenu(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubMenu subMenu = (SubMenu) o;

        if (name != null ? !name.equals(subMenu.name) : subMenu.name != null) return false;
        return dishes != null ? dishes.equals(subMenu.dishes) : subMenu.dishes == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        return "SubMenu{" +
                "name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
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
