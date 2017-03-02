package com.eatmeat.bean;


import javax.measure.unit.Unit;
import java.io.Serializable;
import java.net.URI;
import java.util.*;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class Dish implements Serializable {
    private URI photo;
    private String name;
    private String decsription;
    private Integer price;
    private Map<Unit,List<Integer>> portion = new HashMap<Unit, List<Integer>>();
    private Set<DishMorph> morphs = new HashSet<DishMorph>();
    //delimiters
    private char NAME_VALUE_DELIM = ':';
    private char FIELD_DELIM = ';';


    public Dish(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (NAME_VALUE_DELIM != dish.NAME_VALUE_DELIM) return false;
        if (FIELD_DELIM != dish.FIELD_DELIM) return false;
        if (photo != null ? !photo.equals(dish.photo) : dish.photo != null) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (decsription != null ? !decsription.equals(dish.decsription) : dish.decsription != null) return false;
        if (price != null ? !price.equals(dish.price) : dish.price != null) return false;
        if (portion != null ? !portion.equals(dish.portion) : dish.portion != null) return false;
        return morphs != null ? morphs.equals(dish.morphs) : dish.morphs == null;

    }

    @Override
    public int hashCode() {
        int result = photo != null ? photo.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (decsription != null ? decsription.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        result = 31 * result + (morphs != null ? morphs.hashCode() : 0);
        result = 31 * result + (int) NAME_VALUE_DELIM;
        result = 31 * result + (int) FIELD_DELIM;
        return result;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName()).append("\n").
                append("photo").append(NAME_VALUE_DELIM).append(photo).append(FIELD_DELIM)
                .append("name").append(NAME_VALUE_DELIM).append(photo).append(FIELD_DELIM)
                .append("description").append(NAME_VALUE_DELIM).append(photo).append(FIELD_DELIM)
                .append("price").append(NAME_VALUE_DELIM).append(photo).append(FIELD_DELIM);//TODO finish Dish toString
        return  result.toString();
    }

    public URI getPhoto() {
        return photo;
    }

    public void setPhoto(URI photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecsription() {
        return decsription;
    }

    public void setDescription(String decsription) {
        this.decsription = decsription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Map<Unit, List<Integer>> getPortion() {
        return portion;
    }

    public void setPortion(Map<Unit, List<Integer>> portion) {
        this.portion = portion;
    }

    public Set<DishMorph> getMorphs() {
        return morphs;
    }

    public void setMorphs(Set<DishMorph> morphs) {
        this.morphs = morphs;
    }

    public class DishMorph implements Serializable {
        private String ingridient;
        private Integer price;

        public DishMorph(){

        }

        public DishMorph(String ingridient, Integer price) {
            this.ingridient = ingridient;
            this.price = price;
        }

        public String getIngridient() {
            return ingridient;
        }

        public void setIngridient(String ingridient) {
            this.ingridient = ingridient;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }

}
