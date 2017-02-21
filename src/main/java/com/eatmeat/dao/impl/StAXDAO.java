package com.eatmeat.dao.impl;

import com.eatmeat.bean.Dish;
import com.eatmeat.bean.Menu;
import com.eatmeat.bean.SubMenu;
import com.eatmeat.dao.GenericDAO;
import com.eatmeat.dao.exception.DAOException;
import com.eatmeat.dao.util.MenuTagName;

import javax.measure.unit.SI;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.eatmeat.dao.util.Util.tryParse;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class StAXDAO implements GenericDAO<Menu, String> {

    public String create(Menu newInstance) throws DAOException {
        return null;
    }

    public Menu read(String id) throws DAOException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        Menu menu = null;

        try {
            InputStream inputStream = new FileInputStream(id);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            menu = process(reader);
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found");
        } catch (XMLStreamException e) {
            throw new DAOException("Error while reading XML, see LOG");
            //Todo logging
        }


        return menu;
    }

    private static Menu process(XMLStreamReader reader) throws XMLStreamException {

        Menu menu = new Menu();
        SubMenu subMenu = null;
        Dish dish = null;
        Dish.DishMorph morph = null;
        boolean isMorph = false;
        MenuTagName elementName = null;


        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.valueOf(reader.getLocalName().toUpperCase());

                    switch (elementName) {
                        case MENU:
                            menu.setName(reader.getAttributeValue(null, "name"));
                            break;

                        case SUBMENU:
                            subMenu = new SubMenu();
                            subMenu.setName(reader.getAttributeValue(null, "name"));
                            menu.getSubMenus().add(subMenu);
                            break;

                        case DISH:
                            dish = new Dish();
                            dish.setName(reader.getAttributeValue(null, "name"));
                            subMenu.getDishes().add(dish);
                            break;

                        case PORTION:
                            dish.getPortion().put(SI.GRAM, new ArrayList<>());//initializing portions in the dish
                            dish.getPortion().put(null, new ArrayList<>());
                            break;

                        case MORPHS:
                            isMorph = true;
                            break;

                        case MORPH:
                            morph = dish.new DishMorph();
                            dish.getMorphs().add(morph);
                            break;
                    }

                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();

                    if (text.isEmpty()) {
                        break;
                    }

                    switch (elementName) {
                        case NAME:
                            dish.setName(text);
                            break;

                        case DESCRIPTION:
                            if (isMorph) {
                                morph.setIngridient(text);
                            } else {
                                dish.setDescription(text);
                            }
                            break;

                        case PHOTO:
                            try {
                                dish.setPhoto(new URI(text));
                            } catch (URISyntaxException e) {
                                //todo logging
                            }
                            break;

                        case MASS:
                            dish.getPortion().get(SI.GRAM).add(tryParse(text));
                            break;

                        case COUNT:
                            dish.getPortion().get(null).add(tryParse(text));
                            break;


                        case PRICE:
                            if (isMorph) {
                                morph.setPrice(tryParse(text));
                            } else {
                                dish.setPrice(tryParse(text));
                            }
                            break;


                    }

                    break;

                case XMLStreamConstants.END_ELEMENT:

                    switch (elementName) {
                        case MORPH:
                            dish.getMorphs().add(morph);
                            break;

                        case MORPHS:
                            isMorph = false;
                            break;

                        case DISH:
                            subMenu.getDishes().add(dish);
                            break;

                        case SUBMENU:
                            menu.getSubMenus().add(subMenu);
                            break;
                    }

                    break;

            }

        }

        return menu;
    }

    public void update(Menu transientObject) throws DAOException {

    }

    public void delete(Menu persistentObject) {

    }

    public List find(String fieldName, String value, boolean isPureSearch) {
        return null;
    }
}
