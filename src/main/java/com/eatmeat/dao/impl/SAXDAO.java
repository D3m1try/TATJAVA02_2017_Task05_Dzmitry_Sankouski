package com.eatmeat.dao.impl;

import com.eatmeat.bean.Dish;
import com.eatmeat.bean.Menu;
import com.eatmeat.bean.SubMenu;
import com.eatmeat.dao.GenericDAO;
import com.eatmeat.dao.exception.DAOException;
import com.eatmeat.dao.util.MenuTagName;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.measure.unit.SI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.eatmeat.dao.util.Util.tryParse;

/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class SAXDAO implements GenericDAO<Menu, String> {

    public String create(Menu newInstance) throws DAOException {
        return null;
    }

    public Menu read(String id) throws DAOException {

        MenuSaxHandler handler = new MenuSaxHandler();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(id));
        } catch (SAXException e) {
            e.printStackTrace(); //todo handle exceptions
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return handler.getMenu();
    }

    public void update(Menu transientObject) throws DAOException {

    }

    public void delete(Menu persistentObject) {

    }

    public List find(String fieldName, String value, boolean isPureSearch) {
        return null;
    }


    /**
     * Created by Dzmitry_Sankouski on 20-Feb-17.
     */
    public class MenuSaxHandler extends DefaultHandler {
        private StringBuilder text;
        private Menu menu = new Menu();
        private SubMenu subMenu;
        private Dish dish;
        private Dish.DishMorph morph;
        private boolean isMorph = false;

        public void startElement(String uri, String localName, String qName, Attributes atts) {
            text = new StringBuilder();

            if (qName.equalsIgnoreCase("menu")) {
                menu.setName(atts.getValue("name"));
            }
            if (qName.equalsIgnoreCase("subMenu")) {
                subMenu = new SubMenu();
                subMenu.setName(atts.getValue("name"));
            }
            if (qName.equalsIgnoreCase("dish")) {
                dish = new Dish();
                dish.setName(atts.getValue("name"));
            }
            if (qName.equalsIgnoreCase("portion")) {
                dish.getPortion().put(SI.GRAM, new ArrayList<>());
                dish.getPortion().put(null, new ArrayList<>());
            }
            if (qName.equalsIgnoreCase("morphs")) {
                isMorph = true;
            }
            if (qName.equalsIgnoreCase("morph")) {
                morph = dish.new DishMorph();
            }
        }

        @Override
        public void characters(char[] buffer, int start, int length) {
            text.append(buffer, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));

            switch (tagName) {
                case NAME:
                    dish.setName(text.toString());
                    break;

                case DESCRIPTION:
                    if (isMorph) {
                        morph.setIngridient(text.toString());
                    } else {
                        dish.setDescription(text.toString());
                    }
                    break;

                case PHOTO:
                    try {
                        dish.setPhoto(new URI(text.toString()));
                    } catch (URISyntaxException e) {
                        //todo logging
                    }
                    break;

                case MASS:
                    dish.getPortion().get(SI.GRAM).add(tryParse(text.toString()));
                    break;

                case COUNT:
                    dish.getPortion().get(null).add(tryParse(text.toString()));
                    break;


                case PRICE:
                    if (isMorph) {
                        morph.setPrice(tryParse(text.toString()));
                    } else {
                        dish.setPrice(tryParse(text.toString()));
                    }
                    break;


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
        }


        public Menu getMenu() {
            return menu;
        }

    }


}
