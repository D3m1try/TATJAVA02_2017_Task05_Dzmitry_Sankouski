package com.eatmeat.bean;


import java.io.Serializable;
import java.net.URI;
import java.util.*;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class Dish implements Serializable {
    private URI photo;
    private String name;
    private String decsription;
    private int price;
    private Map<Unit,List<Integer>> portion = new HashMap<Unit, List<Integer>>();
    private Set<DishMorph> morphs = new HashSet<DishMorph>();
    //delimiters
    private char NAME_VALUE_DELIM = ':';
    private char FIELD_DELIM = ';';


    public Dish(){

    }

    //TODO equals & hashcode

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

    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
        private int price;

        public DishMorph(){

        }

        public DishMorph(String ingridient, int price) {
            this.ingridient = ingridient;
            this.price = price;
        }

        public String getIngridient() {
            return ingridient;
        }

        public void setIngridient(String ingridient) {
            this.ingridient = ingridient;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

}
