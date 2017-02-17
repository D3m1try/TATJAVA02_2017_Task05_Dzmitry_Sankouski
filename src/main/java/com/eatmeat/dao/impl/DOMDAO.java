package com.eatmeat.dao.impl;

import com.eatmeat.bean.Dish;
import com.eatmeat.bean.Menu;
import com.eatmeat.bean.SubMenu;
import com.eatmeat.dao.GenericDAO;
import com.eatmeat.dao.exception.DAOException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.measure.unit.SI;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.eatmeat.dao.util.XmlUtil.asList;


/**
 * Created by Dzmitry_Sankouski on 17-Feb-17.
 */
public class DOMDAO implements GenericDAO<Menu, String> {


    public String create(Menu newInstance) throws DAOException {
        return null;
    }

    public Menu read(String id) throws DAOException {

        DOMParser parser = new DOMParser();
        Element rootElement = null;
        Menu menu;
        Dish dish;
        SubMenu subMenu;

        try {
            parser.parse("C:\\Users\\Dzmitry_Sankouski\\IdeaProjects\\TATJAVA02_2017_Task05_Dzmitry_Sankouski\\src\\main\\resources\\menu.xml");
            rootElement = parser.getDocument().getDocumentElement();

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        menu = new Menu();
        menu.setName(rootElement.getAttribute("name"));

        //writing menu
        List<Node> subMenus = asList(rootElement.getChildNodes());
        for (Node subMenuNode :
                subMenus) {//menu iterating

            subMenu = new SubMenu();
            subMenu.setName(subMenuNode.getNodeName());

            //writing sub menu
            List<Node> dishNodes = asList(subMenuNode.getChildNodes());
            for (Node dishNode :
                    dishNodes) {//submenu iterating
                //writing dish
                dish = new Dish();


                NodeList dishChildren = dishNode.getChildNodes();
                dishChildren.item(0);
                dish.setPhoto(URI.create(dishChildren.item(0).getTextContent()));
                dish.setName(dishChildren.item(1).getTextContent());
                dish.setDecsription(dishChildren.item(2).getTextContent());

                //writing portions
                List<Integer> masses = new ArrayList<>();
                List<Integer> counts = new ArrayList<>();
                for (Node portionValue :
                        asList(dishChildren.item(3).getChildNodes())) {
                    if (portionValue.getNodeName().equalsIgnoreCase("mass")) {
                        masses.add(new Integer(Integer.parseInt(portionValue.getTextContent())));
                    }
                    if (portionValue.getNodeName().equalsIgnoreCase("count")) {
                        counts.add(new Integer(Integer.parseInt(portionValue.getTextContent())));
                    }
                }
                dish.getPortion().put(SI.GRAM, masses);
                dish.getPortion().put(null, counts);

                // writing morphs
                if (dishChildren.item(4).getNodeName().equalsIgnoreCase("morphs")) {
                    for (Node morph :
                            asList(dishChildren.item(4).getChildNodes())) { // iterating morphs

                        dish.getMorphs()//adding new dishMorph to dishmorphs
                                .add(dish.new DishMorph(// creating new dishmorph
                                                morph.getFirstChild().getTextContent(),
                                                Integer.parseInt(morph.getLastChild().getTextContent())
                                        )
                                );

                    }
                }

                //writing price
                if (dishChildren.item(4).getNodeName().equalsIgnoreCase("price")) {
                    int price = Integer.parseInt(dishChildren.item(4).getTextContent());
                    dish.setPrice(price);
                }

                subMenu.getDishes().add(dish);
            }
            menu.getSubMenus().add(subMenu);
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
