package com.eatmeat.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class Menu implements Serializable {
    private String name;
    private List<SubMenu> subMenus = new ArrayList<SubMenu>();

    public Menu(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;
        return subMenus != null ? subMenus.equals(menu.subMenus) : menu.subMenus == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (subMenus != null ? subMenus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getName());
        for (SubMenu subMenu :
                this.getSubMenus()) {
            result.append(subMenu.toString()).append("\n");
        }
        return result.toString();
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
