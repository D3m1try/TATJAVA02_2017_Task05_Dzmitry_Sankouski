package com.eatmeat.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class Menu implements Serializable {
    String name;
    List<SubMenu> subMenus = new ArrayList<SubMenu>();

    public Menu(){

    }

    public List<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
